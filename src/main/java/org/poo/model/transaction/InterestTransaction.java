package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class InterestTransaction extends Transaction {
    private double interestRate;
    private double interestAmount;
    public InterestTransaction(int timestamp,
                               double interestRate, double interestAmount) {
        super(timestamp);
        this.description = "Interest Transaction";
        this.interestRate = interestRate;
        this.interestAmount = interestAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getInterestAmount() {
        return interestAmount;
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }

}
