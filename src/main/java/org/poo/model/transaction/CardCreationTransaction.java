package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class CardCreationTransaction extends Transaction {
    private String cardType;
    private String cardNumber;
    public CardCreationTransaction(int timestamp, String description, double amount, String status,
                                   String cardType, String cardNumber) {
        super(timestamp, description, amount, status);
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
