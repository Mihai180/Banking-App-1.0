package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public final class AccountDeletionErrorTransaction extends Transaction {
    public AccountDeletionErrorTransaction(final int timestamp) {
        super(timestamp);
        this.description = "Account couldn't be deleted - there are funds remaining";
    }

    @Override
    public String getType() {
        return "AccountDeletionError";
    }

    @Override
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
