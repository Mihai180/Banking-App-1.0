package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class CardDeletionTransaction extends Transaction {
    private String cardNumber;

    public CardDeletionTransaction(int timestamp, String description, double amount,
                                   String status, String cardNumber) {
        super(timestamp, description, amount, status);
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
