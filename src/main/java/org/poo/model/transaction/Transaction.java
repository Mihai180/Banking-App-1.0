package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public abstract class Transaction {
    protected int timestamp;
    protected String description;
    protected double amount;
    protected String status;

    public Transaction(int timestamp, String description, double amount, String status) {
        this.timestamp = timestamp;
        this.description = description;
        this.amount = amount;
        this.status = status;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public abstract void accept(TransactionVisitor visitor);
}
