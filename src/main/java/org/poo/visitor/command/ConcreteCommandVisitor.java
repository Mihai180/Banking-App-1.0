package org.poo.visitor.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.command.*;
import org.poo.model.account.Account;
import org.poo.model.card.Card;
import org.poo.model.user.User;
import org.poo.service.*;

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
                                  //TransactionService transactionService,
                                  //ReportService reportService,
                                  //MerchantService merchantService,
                                  ExchangeService exchangeService,
                                  ArrayNode output,
                                  ObjectMapper mapper) {
        this.userService = userService;
        this.accountService = accountService;
        this.cardService = cardService;
        //this.transactionService = transactionService;
        //this.reportService = reportService;
        //this.merchantService = merchantService;
        //this.exchangeService = exchangeService;
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
        //addResult("Account created for user: " + command.getEmail() + " with IBAN " + newAccount.getIban());
    }

    @Override
    public void visit(AddFundsCommand command) {
        accountService.addFunds(command.getAccountIBAN(), command.getAmount());
        //addResult("Funds added to account: " + command.getAccountIBAN());
    }

    @Override
    public void visit(CreateCardCommand command) {
        cardService.createCard(command.getAccountIBAN(), command.getCardType(), command.getEmail());
        //addResult("Card created for account: " + command.getAccountIBAN() + " with number: " + createdCard.getCardNumber());
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
        }

        cmdResult.set("output", outputNode);
        this.output.add(cmdResult);
        cmdResult.put("timestamp", command.getTimestamp());
    }

    @Override
    public void visit(DeleteCardCommand command) {
        cardService.deleteCard(command.getCardNumber(), command.getEmail());
    }

    @Override
    public void visit(SetMinBalanceCommand command) {
        accountService.setMinBalance(command.getAccountIban(), command.getMinBalance());
    }

    @Override
    public void visit(PayOnlineCommand command) {
        String result = cardService.payOnline(command.getCardNumber(), command.getAmount(), command.getCurrency(), command.getEmail());

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
    }
}
