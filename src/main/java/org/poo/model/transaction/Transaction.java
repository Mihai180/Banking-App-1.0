package org.poo.model.transaction;

//import org.poo.visitor.transaction.TransactionVisitor;

import org.poo.visitor.transaction.TransactionVisitor;

public abstract class Transaction {
    protected int timestamp;
    protected String description;

    public Transaction(int timestamp) {
        this.description = "";
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public abstract void accept(TransactionVisitor visitor);
}
