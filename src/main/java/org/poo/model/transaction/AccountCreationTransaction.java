package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public final  class AccountCreationTransaction extends Transaction {
    public AccountCreationTransaction(final int timestamp) {
        super(timestamp);
        this.description = "New account created";
    }

    @Override
    public String getType() {
        return "AccountCreation";
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
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
