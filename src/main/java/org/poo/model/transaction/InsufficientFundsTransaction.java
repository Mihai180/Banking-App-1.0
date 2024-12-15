package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class InsufficientFundsTransaction extends Transaction {
    public InsufficientFundsTransaction(int timestamp) {
        super(timestamp);
        this.description = "Insufficient funds";
    }

    public String getType() {
        return "InsufficientFunds";
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
