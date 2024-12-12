package org.poo.visitor.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.command.*;
import org.poo.model.account.Account;
import org.poo.model.card.Card;
import org.poo.model.transaction.*;
import org.poo.model.user.User;
import org.poo.service.*;
import org.poo.visitor.transaction.ConcreteTransactionVisitor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ConcreteCommandVisitor implements CommandVisitor {
    private UserService userService;
    private AccountService accountService;
    private CardService cardService;
    private TransactionService transactionService;
    private ReportService reportService;
    //private MerchantService merchantService;
    private ExchangeService exchangeService;

    private ArrayNode output;
    private ObjectMapper mapper;

    public ConcreteCommandVisitor(UserService userService,
                                  AccountService accountService,
                                  CardService cardService,
                                  TransactionService transactionService,
                                  //ReportService reportService,
                                  //MerchantService merchantService,
                                  ExchangeService exchangeService,
                                  ArrayNode output,
                                  ObjectMapper mapper) {
        this.userService = userService;
        this.accountService = accountService;
        this.cardService = cardService;
        this.transactionService = transactionService;
        //this.reportService = reportService;
        //this.merchantService = merchantService;
        this.exchangeService = exchangeService;
        this.output = output;
        this.mapper = mapper;
    }

    private void addResult(String message) {
        ObjectNode resultNode = mapper.createObjectNode();
        resultNode.put("result", message);
        output.add(resultNode);
    }

    @Override
    public void visit(PrintUsersCommand command) {
        ObjectNode cmdResult = mapper.createObjectNode();
        cmdResult.put("command", command.getCommandName());
        ArrayNode outputUsers = mapper.createArrayNode();
        for (User user : userService.getAllUsers().values()) {
            ObjectNode userNode = mapper.createObjectNode();
            userNode.put("firstName", user.getFirstName());
            userNode.put("lastName", user.getLastName());
            userNode.put("email", user.getEmail());
            ArrayNode accountsArray = mapper.createArrayNode();
            for (Account account : user.getAccounts()) {
                ObjectNode accountNode = mapper.createObjectNode();
                accountNode.put("IBAN", account.getIban());
                accountNode.put("balance", account.getBalance());
                accountNode.put("currency", account.getCurrency());
                String accountType = account.getAccountType();
                accountNode.put("type", accountType);
                ArrayNode cardsArray = mapper.createArrayNode();
                for (Card card : account.getCards()) {
                    ObjectNode cardNode = mapper.createObjectNode();
                    cardNode.put("cardNumber", card.getCardNumber());
                    cardNode.put("status", card.checkStatus());
                    cardsArray.add(cardNode);
                }
                accountNode.set("cards", cardsArray);
                accountsArray.add(accountNode);
            }
            userNode.set("accounts", accountsArray);
            outputUsers.add(userNode);
        }
        cmdResult.set("output", outputUsers);
        this.output.add(cmdResult);
        cmdResult.put("timestamp", command.getTimestamp());
    }

    @Override
    public void visit(AddAccountCommand command) {
        Account newAccount = accountService.createAccount(command.getEmail(), command.getAccountType(), command.getCurrency(), command.getInterestRate());
        if (newAccount != null) {
            Transaction transaction = new AccountCreationTransaction(command.getTimestamp());

            newAccount.addTransaction(transaction);
        }
        //addResult("Account created for user: " + command.getEmail() + " with IBAN " + newAccount.getIban());
    }

    @Override
    public void visit(AddFundsCommand command) {
        accountService.addFunds(command.getAccountIBAN(), command.getAmount());
        //addResult("Funds added to account: " + command.getAccountIBAN());
    }

    @Override
    public void visit(CreateCardCommand command) {
        Card result = cardService.createCard(command.getAccountIBAN(), command.getCardType(), command.getEmail());
        if (result != null) {
            Transaction transaction = new CardCreationTransaction(command.getTimestamp(), command.getEmail(), command.getAccountIBAN(), result.getCardNumber());
            accountService.getAccountByIBAN(command.getAccountIBAN()).addTransaction(transaction);
        }
    }

    @Override
    public void visit(NotImplementedCommand command) {
        addResult("Command '" + command.getCommandName() + "' at timestamp "
                + command.getTimestamp() + " is not implemented yet.");
    }

    @Override
    public void visit(DeleteAccountCommand command) {
        ObjectNode cmdResult = mapper.createObjectNode();
        cmdResult.put("command", command.getCommandName());

        ObjectNode outputNode = mapper.createObjectNode();
        try {
            accountService.deleteAccount(command.getAccount(), command.getEmail());

            outputNode.put("success", "Account deleted");
            outputNode.put("timestamp", command.getTimestamp());
        } catch (Exception e) {
            outputNode.put("error", e.getMessage());
            outputNode.put("timestamp", command.getTimestamp());
        }

        cmdResult.set("output", outputNode);
        this.output.add(cmdResult);
        cmdResult.put("timestamp", command.getTimestamp());
    }

    @Override
    public void visit(DeleteCardCommand command) {
        Card card = cardService.getCardByNumber(command.getCardNumber());
        if (card != null) {
            String iban = card.getAccount().getIban();
            if (iban != null) {
                Transaction transaction = new CardDeletionTransaction(command.getTimestamp(), command.getCardNumber(), command.getEmail(), iban);
                accountService.getAccountByIBAN(iban).addTransaction(transaction);
            }
        }
        cardService.deleteCard(command.getCardNumber(), command.getEmail());
    }

    @Override
    public void visit(SetMinBalanceCommand command) {
        accountService.setMinBalance(command.getAccountIban(), command.getMinBalance());
    }

    @Override
    public void visit(PayOnlineCommand command) {
        String result = cardService.payOnline(command.getCardNumber(), command.getAmount(), command.getCurrency(), command.getEmail());
        //this.output.add(result);
        if (result.equals("Card not found")) {
            ObjectNode cmdResult = mapper.createObjectNode();
            cmdResult.put("command", command.getName());

            ObjectNode outputNode = mapper.createObjectNode();
            outputNode.put("timestamp", command.getTimestamp());
            outputNode.put("description", result);

            cmdResult.set("output", outputNode);
            this.output.add(cmdResult);
            cmdResult.put("timestamp", command.getTimestamp());
        }

        User user = userService.getUserByEmail(command.getEmail());
        if (user != null) {
            Account associatedAccount = null;

            for (Account account : user.getAccounts()) {
                for (Card card : account.getCards()) {
                    if (card.getCardNumber().equals(command.getCardNumber())) {
                        associatedAccount = account;
                        break;
                    }
                }
                if (associatedAccount != null) {
                    break;
                }
            }
            if (associatedAccount != null) {
                if (result.equals("Success")) {
                    double convertedAmount = exchangeService.convertCurrency(command.getCurrency(), associatedAccount.getCurrency(), command.getAmount());
                    DecimalFormat df = new DecimalFormat("#.#########");
                    double formattedAmount = Double.valueOf(df.format(convertedAmount));
                    Transaction transaction = new CardPaymentTransaction(command.getTimestamp(), command.getCommerciant(), formattedAmount);
                    associatedAccount.addTransaction(transaction);
                }
                if (result.equals("Insufficient funds")) {
                    Transaction transaction = new InsufficientFundsTransaction(command.getTimestamp());
                    associatedAccount.addTransaction(transaction);
                }
                if (result.equals("Card is frozen")) {
                    Transaction transaction = new FrozenCardTransaction(command.getTimestamp());
                    associatedAccount.addTransaction(transaction);
                }
            }
        }
    }

    @Override
    public void visit(SendMoneyCommand command) {
        String result = accountService.sendMoney(command.getAccount(), command.getAmount(), command.getReciever());
        if (result.equals("Success") && accountService.getAccountByIBAN(command.getAccount()) != null) {
            String currency = accountService.getAccountByIBAN(command.getAccount()).getCurrency();
            Transaction transaction = new SendMoneyTransaction(command.getTimestamp(), command.getDescription(), command.getAccount(), command.getReciever(),
                    command.getAmount(), currency);
            accountService.getAccountByIBAN(command.getAccount()).addTransaction(transaction);
        }
        if (result.equals("Insufficient funds in sender's account")) {
            Transaction transaction = new InsufficientFundsTransaction(command.getTimestamp());
            accountService.getAccountByIBAN(command.getAccount()).addTransaction(transaction);
        }
    }

    @Override
    public void visit(SetAliasCommand command) {
        userService.setAlias(command.getEmail(), command.getAlias(), command.getAccount());
    }

    @Override
    public void visit(PrintTransactionsCommand command) {
        ObjectNode cmdResult = mapper.createObjectNode();
        cmdResult.put("command", command.getCommand());

        ArrayNode outputTransactions = mapper.createArrayNode();

        List<Transaction> transactions = transactionService.getTransactionsForUser(command.getEmail());
        for (Transaction transaction : transactions) {
            ObjectNode transactionNode = mapper.createObjectNode();

            ConcreteTransactionVisitor transactionVisitor = new ConcreteTransactionVisitor(userService, accountService,
                    cardService, transactionService, transactionNode, mapper);

            transaction.accept(transactionVisitor);
            outputTransactions.add(transactionNode);
        }

        cmdResult.set("output", outputTransactions);
        cmdResult.put("timestamp", command.getTimestamp());

        this.output.add(cmdResult);
    }

    @Override
    public void visit(CheckCardStatusCommand command) {
        String result = cardService.checkCardStatus(command.getCardNumber());
        if (result.equals("Card not found")) {
            ObjectNode cmdResult = mapper.createObjectNode();
            cmdResult.put("command", command.getCommand());
            ObjectNode outputNode = mapper.createObjectNode();
            outputNode.put("timestamp", command.getTimestamp());
            outputNode.put("description", result);
            cmdResult.set("output", outputNode);
            cmdResult.put("timestamp", command.getTimestamp());
            this.output.add(cmdResult);
        }
        if (result.equals("Insufficient funds")) {
            Transaction transaction = new MinimumAmountOfFundsTransaction(command.getTimestamp());
            cardService.getCardByNumber(command.getCardNumber()).getAccount().addTransaction(transaction);
            cardService.getCardByNumber(command.getCardNumber()).block();
        }
    }

    @Override
    public void visit(ChangeInterestRateCommand command) {
        accountService.changeInterestRate(command.getAccount(), command.getIntrestRate());
    }

    @Override
    public void visit(SplitPaymentCommand command) {
        String result = accountService.splitPayment(command.getAccounts(), command.getCurrency(), command.getAmount());
        if (result.equals("Success")) {
            double amount = command.getAmount()/command.getAccounts().size();
            String formattedAmount = String.format("%.2f", command.getAmount());
            Transaction transaction = new SplitPaymentTransaction(command.getTimestamp(), command.getCurrency(), formattedAmount, command.getAccounts(), amount);
            List<String> accounts = command.getAccounts();
            for (String iban : accounts) {
                Account account = accountService.getAccountByIBAN(iban);
                account.addTransaction(transaction);
            }
        }
    }
}
