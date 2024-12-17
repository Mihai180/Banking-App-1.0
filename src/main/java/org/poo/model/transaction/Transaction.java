package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public abstract class Transaction {
    protected int timestamp;
    protected String description;

    public Transaction(final int timestamp) {
        this.description = "";
        this.timestamp = timestamp;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @return
     */
    public abstract String getType();

    /**
     *
     * @param visitor
     */
    public abstract void accept(TransactionVisitor visitor);

    /**
     *
     * @return
     */
    public abstract String getPaymentCommerciant();

    /**
     *
     * @return
     */
    public abstract double getPaymentAmount();
}
