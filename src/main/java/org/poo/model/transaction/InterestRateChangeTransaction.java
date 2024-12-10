package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class InterestRateChangeTransaction extends Transaction {
    private double oldInterestRate;
    private double newInterestRate;
    public InterestRateChangeTransaction(int timestamp,
                                         double oldInterestRate, double newInterestRate) {
        super(timestamp);
        this.description = "InterestRateChange";
        this.oldInterestRate = oldInterestRate;
        this.newInterestRate = newInterestRate;
    }

    public double getOldInterestRate() {
        return oldInterestRate;
    }

    public double getNewInterestRate() {
        return newInterestRate;
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
