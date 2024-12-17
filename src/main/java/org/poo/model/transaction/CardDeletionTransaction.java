package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public final class CardDeletionTransaction extends Transaction {
    private final String cardNumber;
    private final String cardHolder;
    private final String account;

    public CardDeletionTransaction(final int timestamp, final String cardNumber,
                                   final String cardHolder, final String account) {
        super(timestamp);
        this.description = "The card has been destroyed";
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
        return "CardDeletion";
    }

    @Override
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }

}
