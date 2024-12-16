package org.poo.visitor.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.command.ReportCommand;
import org.poo.command.AddInterestCommand;
import org.poo.command.SpendingsReportCommand;
import org.poo.command.SplitPaymentCommand;
import org.poo.command.SetAliasCommand;
import org.poo.command.SendMoneyCommand;
import org.poo.command.PrintTransactionsCommand;
import org.poo.command.PayOnlineCommand;
import org.poo.command.DeleteCardCommand;
import org.poo.command.PrintUsersCommand;
import org.poo.command.SetMinBalanceCommand;
import org.poo.command.ChangeInterestRateCommand;
import org.poo.command.CheckCardStatusCommand;
import org.poo.command.DeleteAccountCommand;
import org.poo.command.CreateCardCommand;
import org.poo.command.AddFundsCommand;
import org.poo.command.NotImplementedCommand;
import org.poo.command.AddAccountCommand;
import org.poo.model.account.Account;
import org.poo.model.card.Card;
import org.poo.model.transaction.SplitPaymentTransaction;
import org.poo.model.transaction.SendMoneyTransaction;
import org.poo.model.transaction.MinimumAmountOfFundsTransaction;
import org.poo.model.transaction.InsufficientFundsTransaction;
import org.poo.model.transaction.InssuficientFundsForSplitTransaction;
import org.poo.model.transaction.CardPaymentTransaction;
import org.poo.model.transaction.CardDeletionTransaction;
import org.poo.model.transaction.CardCreationTransaction;
import org.poo.model.transaction.AccountDeletionErrorTransaction;
import org.poo.model.transaction.AccountCreationTransaction;
import org.poo.model.transaction.FrozenCardTransaction;
import org.poo.model.transaction.InterestRateChangeTransaction;
import org.poo.model.transaction.Transaction;
import org.poo.model.user.User;
import org.poo.service.CardService;
import org.poo.service.AccountService;
import org.poo.service.ExchangeService;
import org.poo.service.UserService;
import org.poo.service.TransactionService;
import org.poo.visitor.transaction.ConcreteTransactionVisitor;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.ArrayList;


public final class ConcreteCommandVisitor implements CommandVisitor {
    private final UserService userService;
    private final AccountService accountService;
    private final CardService cardService;
    private final TransactionService transactionService;
    private final ExchangeService exchangeService;
    private final ArrayNode output;
    private final ObjectMapper mapper;
    private static final int DECIMAL_PRECISION = 14;

    public ConcreteCommandVisitor(final UserService userService,
                                  final AccountService accountService,
                                  final CardService cardService,
                                  final TransactionService transactionService,
                                  final ExchangeService exchangeService,
                                  final ArrayNode output,
                                  final ObjectMapper mapper) {
        this.userService = userService;
        this.accountService = accountService;
        this.cardService = cardService;
        this.transactionService = transactionService;
        this.exchangeService = exchangeService;
        this.output = output;
        this.mapper = mapper;
    }

    private void notFoundResult(final String commandName, final String description,
                                final int timestamp) {
        ObjectNode cmdResult = mapper.createObjectNode();
        cmdResult.put("command", commandName);
        ObjectNode outputNode = mapper.createObjectNode();
        outputNode.put("description", description);
        outputNode.put("timestamp", timestamp);
        cmdResult.set("output", outputNode);
        cmdResult.put("timestamp", timestamp);
        this.output.add(cmdResult);
    }

    private ObjectNode createReportOutputNode(final Account account,
                                              final List<Transaction> transactions,
                                              final long startTimestamp,
                                              final long endTimestamp,
                                              final String transactionType,
                                              final Map<String, Double> commerciantsTotals) {
        ObjectNode outputNode = mapper.createObjectNode();
        outputNode.put("IBAN", account.getIban());
        outputNode.put("balance", account.getBalance());
        outputNode.put("currency", account.getCurrency());

        ArrayNode transactionsArray = mapper.createArrayNode();
        for (Transaction transaction : transactions) {
            if (transaction.getTimestamp() >= startTimestamp
                    && transaction.getTimestamp() <= endTimestamp
            && (transactionType == null || transaction.getType().equals(transactionType))) {
                ObjectNode transactionNode = mapper.createObjectNode();
                ConcreteTransactionVisitor transactionVisitor =
                        new ConcreteTransactionVisitor(transactionNode, mapper);
                transaction.accept(transactionVisitor);
                transactionsArray.add(transactionNode);
                if ("CardPayment".equals(transaction.getType()) && commerciantsTotals != null) {
                    String commerciant = ((CardPaymentTransaction) transaction).getCommerciant();
                    double amount = ((CardPaymentTransaction) transaction).getAmount();
                    commerciantsTotals.merge(commerciant, amount, Double::sum);
                }
            }
        }
        outputNode.set("transactions", transactionsArray);
        return outputNode;
    }

    private void notSavingsAccountResult(final String commandName, final String description,
                                         final long timestamp) {
        ObjectNode cmdResult = mapper.createObjectNode();
        cmdResult.put("command", commandName);
        ObjectNode outputNode = mapper.createObjectNode();
        outputNode.put("timestamp", timestamp);
        outputNode.put("description", description);
        cmdResult.set("output", outputNode);
        cmdResult.put("timestamp", timestamp);
        this.output.add(cmdResult);
    }

    private void convert(final PayOnlineCommand command, final Account associatedAccount) {
        double convertedAmount = exchangeService.convertCurrency(command.getCurrency(),
                associatedAccount.getCurrency(), command.getAmount());
        DecimalFormat df = new DecimalFormat("#.#########");
        double formattedAmount = Double.valueOf(df.format(convertedAmount));
        Transaction transaction = new CardPaymentTransaction(command.getTimestamp(),
                command.getCommerciant(), formattedAmount);
        associatedAccount.addTransaction(transaction);
    }

    @Override
    public void visit(final PrintUsersCommand command) {
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
    public void visit(final AddAccountCommand command) {
        Account newAccount = accountService.createAccount(command.getEmail(),
                command.getAccountType(), command.getCurrency(), command.getInterestRate());
        if (newAccount != null) {
            Transaction transaction = new AccountCreationTransaction(command.getTimestamp());
            newAccount.addTransaction(transaction);
        }
    }

    @Override
    public void visit(final AddFundsCommand command) {
        accountService.addFunds(command.getAccountIBAN(), command.getAmount());
    }

    @Override
    public void visit(final CreateCardCommand command) {
        Card result = cardService.createCard(command.getAccountIBAN(), command.getCardType(),
                command.getEmail());
        if (result != null) {
            Transaction transaction = new CardCreationTransaction(command.getTimestamp(),
                    command.getEmail(), command.getAccountIBAN(), result.getCardNumber());
            accountService.getAccountByIBAN(command.getAccountIBAN()).addTransaction(transaction);
        }
    }

    @Override
    public void visit(final NotImplementedCommand command) {
        ObjectNode resultNode = mapper.createObjectNode();
        resultNode.put("result", "Command '" + command.getCommandName()
                + "' at timestamp " + command.getTimestamp() + " is not implemented yet.");
        output.add(resultNode);
    }

    @Override
    public void visit(final DeleteAccountCommand command) {
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
            Transaction transaction = new AccountDeletionErrorTransaction(command.getTimestamp());
            accountService.getAccountByIBAN(command.getAccount()).addTransaction(transaction);
        }

        cmdResult.set("output", outputNode);
        this.output.add(cmdResult);
        cmdResult.put("timestamp", command.getTimestamp());
    }

    @Override
    public void visit(final DeleteCardCommand command) {
        Card card = cardService.getCardByNumber(command.getCardNumber());
        if (card != null) {
            String iban = card.getAccount().getIban();
            if (iban != null) {
                Transaction transaction = new CardDeletionTransaction(command.getTimestamp(),
                        command.getCardNumber(), command.getEmail(), iban);
                accountService.getAccountByIBAN(iban).addTransaction(transaction);
            }
        }
        cardService.deleteCard(command.getCardNumber(), command.getEmail());
    }

    @Override
    public void visit(final SetMinBalanceCommand command) {
        accountService.setMinBalance(command.getAccountIban(), command.getMinBalance());
    }

    @Override
    public void visit(final PayOnlineCommand command) {
        String result = cardService.payOnline(command.getCardNumber(), command.getAmount(),
                command.getCurrency(), command.getEmail());
        if (result.equals("Card not found")
                || result.equals("You can't pay this amount because is used")) {
            ObjectNode cmdResult = mapper.createObjectNode();
            cmdResult.put("command", command.getName());

            ObjectNode outputNode = mapper.createObjectNode();
            outputNode.put("timestamp", command.getTimestamp());
            outputNode.put("description", "Card not found");

            cmdResult.set("output", outputNode);
            this.output.add(cmdResult);
            cmdResult.put("timestamp", command.getTimestamp());
        }

        Card card = cardService.getCardByNumber(command.getCardNumber());
        if (card != null) {
            Account associatedAccount = card.getAccount();
            if (result.equals("Success")) {
                convert(command, associatedAccount);
            }
            if (result.equals("Insufficient funds")) {
                Transaction transaction = new InsufficientFundsTransaction(command.getTimestamp());
                associatedAccount.addTransaction(transaction);
            }
            if (result.equals("Card is frozen")) {
                Transaction transaction = new FrozenCardTransaction(command.getTimestamp());
                associatedAccount.addTransaction(transaction);
            }
            if (result.startsWith("New card generated successfully")) {
                String[] resultParts = result.split(": ");
                if (resultParts.length > 1) {
                    String newCardNumber = resultParts[1];
                    convert(command, associatedAccount);
                    Transaction transaction1 = new CardDeletionTransaction(command.getTimestamp(),
                            command.getCardNumber(), command.getEmail(),
                            associatedAccount.getIban());
                    associatedAccount.addTransaction(transaction1);
                    Transaction transaction2 = new CardCreationTransaction(command.getTimestamp(),
                            command.getEmail(), associatedAccount.getIban(), newCardNumber);
                    associatedAccount.addTransaction(transaction2);
                }
            }
        }
    }

    @Override
    public void visit(final SendMoneyCommand command) {
        String result = accountService.sendMoney(command.getAccount(), command.getAmount(),
                command.getReciever());
        if (result.equals("Success")
                && accountService.getAccountByIBAN(command.getAccount()) != null) {
            String currency = accountService.getAccountByIBAN(command.getAccount()).getCurrency();
            Transaction transaction = new SendMoneyTransaction(command.getTimestamp(),
                    command.getDescription(), command.getAccount(), command.getReciever(),
                    command.getAmount(), currency, "sent");
            accountService.getAccountByIBAN(command.getAccount()).addTransaction(transaction);
        }
        if (result.equals("Success")
                && accountService.getAccountByIBAN(command.getReciever()) != null) {
            String currency = accountService.getAccountByIBAN(command.getReciever()).getCurrency();
            double convertedAmount =
                    exchangeService.convertCurrency(accountService.getAccountByIBAN(
                            command.getAccount()).getCurrency(), currency,
                            command.getAmount());
            BigDecimal preciseAmount =
                    new BigDecimal(convertedAmount).setScale(DECIMAL_PRECISION, RoundingMode.DOWN);
            double finalAmount = preciseAmount.doubleValue();
            Transaction transaction =
                    new SendMoneyTransaction(command.getTimestamp(),
                            command.getDescription(), command.getAccount(),
                            command.getReciever(),
                            finalAmount, currency, "received");
            accountService.getAccountByIBAN(command.getReciever()).addTransaction(transaction);
        }
        if (result.equals("Insufficient funds in sender's account")) {
            Transaction transaction = new InsufficientFundsTransaction(command.getTimestamp());
            accountService.getAccountByIBAN(command.getAccount()).addTransaction(transaction);
        }
    }

    @Override
    public void visit(final SetAliasCommand command) {
        userService.setAlias(command.getEmail(), command.getAlias(), command.getAccount());
    }

    @Override
    public void visit(final PrintTransactionsCommand command) {
        ObjectNode cmdResult = mapper.createObjectNode();
        cmdResult.put("command", command.getCommand());

        ArrayNode outputTransactions = mapper.createArrayNode();

        List<Transaction> transactions =
                transactionService.getTransactionsForUser(command.getEmail());
        transactions.sort(Comparator.comparing(Transaction::getTimestamp));
        for (Transaction transaction : transactions) {
            ObjectNode transactionNode = mapper.createObjectNode();

            ConcreteTransactionVisitor transactionVisitor =
                    new ConcreteTransactionVisitor(transactionNode, mapper);

            transaction.accept(transactionVisitor);
            outputTransactions.add(transactionNode);
        }

        cmdResult.set("output", outputTransactions);
        cmdResult.put("timestamp", command.getTimestamp());

        this.output.add(cmdResult);
    }

    @Override
    public void visit(final CheckCardStatusCommand command) {
        String result = cardService.checkCardStatus(command.getCardNumber());
        if (result.equals("Card not found")) {
            notFoundResult(command.getCommand(), result, command.getTimestamp());
        }
        if (result.equals("Insufficient funds")) {
            Transaction transaction = new MinimumAmountOfFundsTransaction(command.getTimestamp());
            Account account = cardService.getCardByNumber(command.getCardNumber()).getAccount();
            account.addTransaction(transaction);
            cardService.getCardByNumber(command.getCardNumber()).block();
        }
    }

    @Override
    public void visit(final ChangeInterestRateCommand command) {
        String result = accountService.changeInterestRate(command.getAccount(),
                command.getIntrestRate());
        if (result.equals("This is not a savings account")) {
            notSavingsAccountResult(command.getCommand(), result, command.getTimestamp());
        }
        if (result.equals("Success")) {
            Transaction transaction = new InterestRateChangeTransaction(command.getTimestamp(),
                    command.getIntrestRate());
            accountService.getAccountByIBAN(command.getAccount()).addTransaction(transaction);
        }
    }

    @Override
    public void visit(final SplitPaymentCommand command) {
        String result = accountService.splitPayment(command.getAccounts(),
                command.getCurrency(), command.getAmount());
        if (result.equals("Success")) {
            double amount = command.getAmount() / command.getAccounts().size();
            String formattedAmount = String.format("%.2f", command.getAmount());
            Transaction transaction = new SplitPaymentTransaction(command.getTimestamp(),
                    command.getCurrency(), formattedAmount, command.getAccounts(), amount);
            List<String> accounts = command.getAccounts();
            for (String iban : accounts) {
                Account account = accountService.getAccountByIBAN(iban);
                account.addTransaction(transaction);
            }
        }
        String regex = "Account \\S+ has insufficient funds for a split payment\\.";
        if (result.trim().matches(regex)) {
            double amount = command.getAmount() / command.getAccounts().size();
            Transaction transaction = new InssuficientFundsForSplitTransaction(command.getAmount(),
                    command.getCurrency(), command.getAccounts(), command.getTimestamp(),
                    result, amount);
            List<String> accounts = command.getAccounts();
            for (String iban : accounts) {
                Account account = accountService.getAccountByIBAN(iban);
                account.addTransaction(transaction);
            }
        }
    }

    @Override
    public void visit(final ReportCommand command) {
        ObjectNode reportResult = mapper.createObjectNode();
        reportResult.put("command", command.getCommand());
        Account account = accountService.getAccountByIBAN(command.getAccount());
        if (account == null) {
            notFoundResult(command.getCommand(), "Account not found",
                    command.getTimestamp());
            return;
        }
        List<Transaction> transactions = accountService.getTransactions(command.getAccount());
        ObjectNode outputNode;
        outputNode = createReportOutputNode(account, transactions, command.getStartTimestamp(),
                command.getEndTimestamp(), null, null);
        reportResult.set("output", outputNode);
        reportResult.put("timestamp", command.getTimestamp());
        this.output.add(reportResult);
    }

    @Override
    public void visit(final SpendingsReportCommand command) {
        ObjectNode reportResult = mapper.createObjectNode();
        reportResult.put("command", command.getCommand());
        Account account = accountService.getAccountByIBAN(command.getAccount());
        if (account == null) {
            notFoundResult(command.getCommand(), "Account not found",
                    command.getTimestamp());
            return;
        }
        if (account.getAccountType().equals("savings")) {
            ObjectNode cmdResult = mapper.createObjectNode();
            cmdResult.put("command", command.getCommand());
            ObjectNode outputNode = mapper.createObjectNode();
            outputNode.put("error",
                    "This kind of report is not supported for a saving account");
            cmdResult.set("output", outputNode);
            cmdResult.put("timestamp", command.getTimestamp());
            this.output.add(cmdResult);
            return;
        }
        Map<String, Double> commerciantsTotals = new HashMap<>();
        List<Transaction> transactions = accountService.getTransactions(command.getAccount());
        ObjectNode outputNode;
        outputNode = createReportOutputNode(account, transactions,
                command.getStartTimestamp(), command.getEndTimestamp(),
                "CardPayment", commerciantsTotals);
        List<Map.Entry<String, Double>> commerciantsList =
                new ArrayList<>(commerciantsTotals.entrySet());
        commerciantsList.sort(Map.Entry.comparingByKey());
        ArrayNode commerciantsArray = mapper.createArrayNode();
        for (Map.Entry<String, Double> entry : commerciantsList) {
            ObjectNode commerciantNode = mapper.createObjectNode();
            commerciantNode.put("commerciant", entry.getKey());
            commerciantNode.put("total", entry.getValue());
            commerciantsArray.add(commerciantNode);
        }

        outputNode.set("commerciants", commerciantsArray);
        reportResult.set("output", outputNode);
        reportResult.put("timestamp", command.getTimestamp());
        this.output.add(reportResult);
    }

    @Override
    public void visit(final AddInterestCommand command) {
        String result = accountService.addInterestRate(command.getAccount());
        if (result.equals("This is not a savings account")) {
            notSavingsAccountResult(command.getCommand(), result, command.getTimestamp());
        }
    }
}
