package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class BankTransferTransaction extends Transaction {
    private String fromAccountIban;
    private String toAccountIban;
    private String currency;

    public BankTransferTransaction(int timestamp,
                                   String fromAccountIban, String toAccountIban, String currency) {
        super(timestamp);
        this.description = "BankTransfer";
        this.fromAccountIban = fromAccountIban;
        this.toAccountIban = toAccountIban;
        this.currency = currency;
    }

    public String getFromAccountIban() {
        return fromAccountIban;
    }

    public String getToAccountIban() {
        return toAccountIban;
    }

    public String getCurrency() {
        return currency;
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }

}

