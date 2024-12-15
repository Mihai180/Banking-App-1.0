package org.poo.model.transaction;

//import org.poo.visitor.transaction.TransactionVisitor;

import org.poo.visitor.transaction.TransactionVisitor;

public class AccountCreationTransaction extends Transaction {
    public AccountCreationTransaction(int timestamp) {
        super(timestamp);
        this.description = "New account created";
    }

    @Override
    public String getType() {
        return "AccountCreation";
    }

    @Override
    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
