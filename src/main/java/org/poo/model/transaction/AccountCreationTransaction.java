package org.poo.model.transaction;

//import org.poo.visitor.transaction.TransactionVisitor;

import org.poo.visitor.transaction.TransactionVisitor;

public class AccountCreationTransaction extends Transaction {
    public AccountCreationTransaction(int timestamp) {
        super(timestamp);
        this.description = "New account created";
    }

    /*public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }

     */
    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
