package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public final class InterestRateChangeTransaction extends Transaction {
    public InterestRateChangeTransaction(final int timestamp, final double newInterestRate) {
        super(timestamp);
        this.description = "Interest rate of the account changed to " + newInterestRate;
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
        return "InterestRateChange";
    }

    @Override
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
