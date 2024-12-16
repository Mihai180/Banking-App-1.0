package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public final class MinimumAmountOfFundsTransaction extends Transaction {
    public MinimumAmountOfFundsTransaction(final int timestamp) {
        super(timestamp);
        this.description = "You have reached the minimum amount of funds, the card will be frozen";
    }

    @Override
    public String getType() {
        return "MinimumAmountOfFunds";
    }

    @Override
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
