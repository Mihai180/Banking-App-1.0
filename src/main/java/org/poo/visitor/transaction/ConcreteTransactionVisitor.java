package org.poo.visitor.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.model.transaction.*;
import org.poo.service.AccountService;
import org.poo.service.CardService;
import org.poo.service.TransactionService;
import org.poo.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConcreteTransactionVisitor implements TransactionVisitor {
    private UserService userService;
    private AccountService accountService;
    private CardService cardService;
    private TransactionService transactionService;

    private ObjectNode transactionNode;

    public ConcreteTransactionVisitor(UserService userService,
                                      AccountService accountService,
                                      CardService cardService,
                                      TransactionService transactionService,
                                      ObjectNode transactionNode) {
        this.userService = userService;
        this.accountService = accountService;
        this.cardService = cardService;
        this.transactionService = transactionService;
        this.transactionNode = transactionNode;
    }

    public void visit(AccountCreationTransaction transaction) {

    }

    public void visit(SendMoneyTransaction sendMoneyTransaction) {
        double amount = sendMoneyTransaction.getAmount();
        String currency = sendMoneyTransaction.getCurrency();

        String amountWithCurrency = String.format("%.1f %s", amount, currency);

        transactionNode.put("senderIBAN", sendMoneyTransaction.getSender());
        transactionNode.put("receiverIBAN", sendMoneyTransaction.getReceiver());
        transactionNode.put("amount", amountWithCurrency);
        transactionNode.put("transferType", "sent");
    }

    public void visit(BankTransferTransaction transaction){

    }

    public void visit(CardCreationTransaction transaction) {

    }

    public void visit(CardDeletionTransaction transaction) {

    }

    public void visit(InterestRateChangeTransaction transaction) {

    }

    public void visit(InterestTransaction transaction) {

    }

    public void visit(MinBalanceSettingTransaction transaction){

    }

    public void visit(PaymentTransaction transaction) {

    }

    public void visit(SplitPaymentTransaction transaction) {

    }

    public void visit(ErrorTransaction transaction) {

    }

}
