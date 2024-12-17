package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public final class CardPaymentTransaction extends Transaction {
    private final String commerciant;
    private final double amount;

    public CardPaymentTransaction(final int timestamp, final String commerciant,
                                  final double amount) {
        super(timestamp);
        this.description = "Card payment";
        this.commerciant = commerciant;
        this.amount = amount;
    }

    @Override
    public String getPaymentCommerciant() {
        return commerciant;
    }

    @Override
    public double getPaymentAmount() {
        return amount;
    }

    @Override
    public String getType() {
        return "CardPayment";
    }

    @Override
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
