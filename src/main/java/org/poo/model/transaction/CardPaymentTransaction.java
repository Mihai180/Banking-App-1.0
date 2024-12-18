package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

/**
 * Clasa finală CardPaymentTransaction reprezintă o tranzacție specifică pentru
 * efectuarea unei plăți
 */
public final class CardPaymentTransaction extends Transaction {
    private final String commerciant;
    private final double amount;

    public CardPaymentTransaction(final int timestamp, final String commerciant,
                                  final double amount) {
        super(timestamp);
        this.description = "Card payment";
        this.commerciant = commerciant;
        this.amount = amount;
    }

    /**
     * Returnează numele comerciantului implicat în tranzacție
     * @return numele comerciantului
     */
    @Override
    public String getPaymentCommerciant() {
        return commerciant;
    }

    /**
     * Returnează suma plății asociate tranzacției
     * @return suma plătită
     */
    @Override
    public double getPaymentAmount() {
        return amount;
    }

    @Override
    public String getType() {
        return "CardPayment";
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
