package org.poo.visitor.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.model.transaction.*;
import org.poo.service.AccountService;
import org.poo.service.CardService;
import org.poo.service.TransactionService;
import org.poo.service.UserService;

import java.util.List;

public class ConcreteTransactionVisitor implements TransactionVisitor {
    private UserService userService;
    private AccountService accountService;
    private CardService cardService;
    private TransactionService transactionService;
    private ObjectMapper mapper;

    private ObjectNode transactionNode;

    public ConcreteTransactionVisitor(UserService userService,
                                      AccountService accountService,
                                      CardService cardService,
                                      TransactionService transactionService,
                                      ObjectNode transactionNode,
                                      ObjectMapper mapper) {
        this.userService = userService;
        this.accountService = accountService;
        this.cardService = cardService;
        this.transactionService = transactionService;
        this.transactionNode = transactionNode;
        this.mapper = mapper;
    }

    public void visit(AccountCreationTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
    }

    public void visit(SendMoneyTransaction sendMoneyTransaction) {
        transactionNode.put("timestamp", sendMoneyTransaction.getTimestamp());
        transactionNode.put("description", sendMoneyTransaction.getDescription());
        double amount = sendMoneyTransaction.getAmount();
        String currency = sendMoneyTransaction.getCurrency();

        String amountWithCurrency = amount + " " + currency;

        transactionNode.put("senderIBAN", sendMoneyTransaction.getSender());
        transactionNode.put("receiverIBAN", sendMoneyTransaction.getReceiver());
        transactionNode.put("amount", amountWithCurrency);
        transactionNode.put("transferType", sendMoneyTransaction.getTransferType());
    }

    public void visit(CardCreationTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
        transactionNode.put("card", transaction.getCardNumber());
        transactionNode.put("cardHolder", transaction.getCardHolder());
        transactionNode.put("account", transaction.getAccount());
    }

    public void visit(CardPaymentTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
        transactionNode.put("amount", transaction.getAmount());
        transactionNode.put("commerciant", transaction.getCommerciant());
    }

    public void visit(InsufficientFundsTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
    }

    public void visit(CardDeletionTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
        transactionNode.put("card", transaction.getCardNumber());
        transactionNode.put("cardHolder", transaction.getCardHolder());
        transactionNode.put("account", transaction.getAccount());
    }

    public void visit(MinimumAmountOfFundsTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
    }

    @Override
    public void visit(FrozenCardTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
    }

    @Override
    public void visit(SplitPaymentTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription() + transaction.getAmount() + " " + transaction.getCurrency());
        transactionNode.put("currency", transaction.getCurrency());
        transactionNode.put("amount", transaction.getSplitAmount());
        ArrayNode involvedAccountsArray = mapper.createArrayNode();
        for (String iban : transaction.getInvolvedAccounts()) {
            involvedAccountsArray.add(iban);
        }
        transactionNode.set("involvedAccounts", involvedAccountsArray);
    }

    @Override
    public void visit(InssuficientFundsForSplitTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
        transactionNode.put("currency", transaction.getCurrency());
        transactionNode.put("amount", transaction.getSplitAmount());
        ArrayNode involvedAccountsArray = mapper.createArrayNode();
        for (String iban : transaction.getInvolvedAccounts()) {
            involvedAccountsArray.add(iban);
        }
        transactionNode.set("involvedAccounts", involvedAccountsArray);
        transactionNode.put("error", transaction.getError());
    }

    public void visit(AccountDeletionErrorTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
    }

    public void visit(BankTransferTransaction transaction){

    }

    public void visit(InterestRateChangeTransaction transaction) {

    }

    public void visit(InterestTransaction transaction) {

    }

    public void visit(MinBalanceSettingTransaction transaction){

    }

    public void visit(ErrorTransaction transaction) {

    }

}
