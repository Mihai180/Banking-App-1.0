package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

/**
 * Clasa finală CardDeletionTransaction reprezintă o tranzacție specifică
 * pentru ștergerea unui card
 */
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

    /**
     * Folosită doar la card payment transaction
     * @return null
     */
    @Override
    public String getPaymentCommerciant() {
        return null;
    }

    /**
     * Folosită doar la card payment transaction
     * @return 0
     */
    @Override
    public double getPaymentAmount() {
        return 0;
    }

    @Override
    public String getType() {
        return "CardDeletion";
    }

    /**
     * Acceptă un vizitator
     * @param visitor este obiectul care implementează interfața TransactionVisitor
     */
    @Override
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
