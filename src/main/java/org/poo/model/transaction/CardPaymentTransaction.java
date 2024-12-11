package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class CardPaymentTransaction extends Transaction {
    private String commerciant;
    private double amount;

    public CardPaymentTransaction(int timestamp, String commerciant, double amount) {
        super(timestamp);
        this. description = "Card payment";
        this.commerciant = commerciant;
        this.amount = amount;
    }

    public String getCommerciant() {
        return commerciant;
    }

    public double getAmount() {
        return amount;
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
