package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

/**
 * Clasa finală InsufficientFundsTransaction reprezintă o tranzacție specifică care indică
 * o eroare de fonduri insuficiente în timpul unei operațiuni
 */
public final class InsufficientFundsTransaction extends Transaction {
    public InsufficientFundsTransaction(final int timestamp) {
        super(timestamp);
        this.description = "Insufficient funds";
    }

    @Override
    public String getType() {
        return "InsufficientFunds";
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
