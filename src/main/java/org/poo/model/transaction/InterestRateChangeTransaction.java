package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class InterestRateChangeTransaction extends Transaction {
    public InterestRateChangeTransaction(int timestamp, double newInterestRate) {
        super(timestamp);
        this.description = "Interest rate of the account changed to " + newInterestRate;
    }

    public String getType() {
        return "InterestRateChange";
    }

    @Override
    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
