package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class CardDeletionTransaction extends Transaction {
    private String cardNumber;

    public CardDeletionTransaction(int timestamp, String cardNumber) {
        super(timestamp);
        this.description = "CardDeletion";
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }

}
