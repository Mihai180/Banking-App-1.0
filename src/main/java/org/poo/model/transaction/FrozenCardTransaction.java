package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public final class FrozenCardTransaction extends Transaction {
    public FrozenCardTransaction(final int timestamp) {
        super(timestamp);
        this.description = "The card is frozen";
    }

    @Override
    public String getType() {
        return "FrozenCard";
    }

    /**
     *
     * @param visitor
     */
    @Override
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
