package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public final class InsufficientFundsTransaction extends Transaction {
    public InsufficientFundsTransaction(final int timestamp) {
        super(timestamp);
        this.description = "Insufficient funds";
    }

    @Override
    public String getType() {
        return "InsufficientFunds";
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
