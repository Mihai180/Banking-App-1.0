package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

/**
 * Clasa finală MinimumAmountOfFundsTransaction reprezintă o tranzacție specifică care indică
 * atingerea sumei minime de fonduri într-un cont
 */
public final class MinimumAmountOfFundsTransaction extends Transaction {
    public MinimumAmountOfFundsTransaction(final int timestamp) {
        super(timestamp);
        this.description = "You have reached the minimum amount of funds, the card will be frozen";
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
        return "MinimumAmountOfFunds";
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
