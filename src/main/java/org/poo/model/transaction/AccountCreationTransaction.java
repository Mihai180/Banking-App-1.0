package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

/**
 * Clasa finală AccountCreationTransaction reprezintă o tranzacție
 * specifică pentru crearea unui cont nou
 */
public final  class AccountCreationTransaction extends Transaction {
    public AccountCreationTransaction(final int timestamp) {
        super(timestamp);
        this.description = "New account created";
    }

    @Override
    public String getType() {
        return "AccountCreation";
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

    /**
     * Acceptă un vizitator
     * @param visitor este obiectul care implementează interfața TransactionVisitor
     */
    @Override
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
