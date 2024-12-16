package org.poo.visitor.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.model.transaction.AccountCreationTransaction;
import org.poo.model.transaction.AccountDeletionErrorTransaction;
import org.poo.model.transaction.CardCreationTransaction;
import org.poo.model.transaction.CardDeletionTransaction;
import org.poo.model.transaction.CardPaymentTransaction;
import org.poo.model.transaction.FrozenCardTransaction;
import org.poo.model.transaction.InssuficientFundsForSplitTransaction;
import org.poo.model.transaction.InsufficientFundsTransaction;
import org.poo.model.transaction.InterestRateChangeTransaction;
import org.poo.model.transaction.MinimumAmountOfFundsTransaction;
import org.poo.model.transaction.SendMoneyTransaction;
import org.poo.model.transaction.SplitPaymentTransaction;

public final class ConcreteTransactionVisitor implements TransactionVisitor {
    private final ObjectMapper mapper;
    private final ObjectNode transactionNode;

    public ConcreteTransactionVisitor(final ObjectNode transactionNode, final ObjectMapper mapper) {
        this.transactionNode = transactionNode;
        this.mapper = mapper;
    }

    @Override
    public void visit(final AccountCreationTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
    }

    @Override
    public void visit(final SendMoneyTransaction sendMoneyTransaction) {
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

    @Override
    public void visit(final CardCreationTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
        transactionNode.put("card", transaction.getCardNumber());
        transactionNode.put("cardHolder", transaction.getCardHolder());
        transactionNode.put("account", transaction.getAccount());
    }

    @Override
    public void visit(final CardPaymentTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
        transactionNode.put("amount", transaction.getAmount());
        transactionNode.put("commerciant", transaction.getCommerciant());
    }

    @Override
    public void visit(final InsufficientFundsTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
    }

    @Override
    public void visit(final CardDeletionTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
        transactionNode.put("card", transaction.getCardNumber());
        transactionNode.put("cardHolder", transaction.getCardHolder());
        transactionNode.put("account", transaction.getAccount());
    }

    @Override
    public void visit(final MinimumAmountOfFundsTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
    }

    @Override
    public void visit(final FrozenCardTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
    }

    @Override
    public void visit(final SplitPaymentTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription()
                + transaction.getAmount() + " " + transaction.getCurrency());
        transactionNode.put("currency", transaction.getCurrency());
        transactionNode.put("amount", transaction.getSplitAmount());
        ArrayNode involvedAccountsArray = mapper.createArrayNode();
        for (String iban : transaction.getInvolvedAccounts()) {
            involvedAccountsArray.add(iban);
        }
        transactionNode.set("involvedAccounts", involvedAccountsArray);
    }

    @Override
    public void visit(final InssuficientFundsForSplitTransaction transaction) {
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

    @Override
    public void visit(final AccountDeletionErrorTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
    }

    @Override
    public void visit(final InterestRateChangeTransaction transaction) {
        transactionNode.put("timestamp", transaction.getTimestamp());
        transactionNode.put("description", transaction.getDescription());
    }
}
