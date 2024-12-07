package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class InterestTransaction extends Transaction {
    private double interestRate;
    private double interestAmount;
    public InterestTransaction(int timestamp, String description, double amount, String status,
                               double interestRate, double interestAmount) {
        super(timestamp, description, amount, status);
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
