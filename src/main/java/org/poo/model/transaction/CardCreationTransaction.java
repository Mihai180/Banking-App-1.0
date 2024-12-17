package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public final class CardCreationTransaction extends Transaction {
    private final String cardNumber;
    private final String cardHolder;
    private final String account;

    public CardCreationTransaction(final int timestamp, final String cardHolder,
                                   final String account, final String cardNumber) {
        super(timestamp);
        this.description = "New card created";
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.account = account;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getAccount() {
        return account;
    }

    @Override
    public String getPaymentCommerciant() {
        return null;
    }

    @Override
    public double getPaymentAmount() {
        return 0;
    }

    @Override
    public String getType() {
        return "CardCreation";
    }

    @Override
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
