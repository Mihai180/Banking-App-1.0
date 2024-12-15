package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class FrozenCardTransaction extends Transaction {
    public FrozenCardTransaction(int timestamp) {
        super(timestamp);
        this.description = "The card is frozen";
    }

    public String getType() {
        return "FrozenCard";
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
