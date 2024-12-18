package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

/**
 * Clasa finală InterestRateChangeTransaction reprezintă o tranzacție specifică care indică
 * schimbarea ratei dobânzii unui cont
 */
public final class InterestRateChangeTransaction extends Transaction {
    public InterestRateChangeTransaction(final int timestamp, final double newInterestRate) {
        super(timestamp);
        this.description = "Interest rate of the account changed to " + newInterestRate;
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
        return "InterestRateChange";
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
