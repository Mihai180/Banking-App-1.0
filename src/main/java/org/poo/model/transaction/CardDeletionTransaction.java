package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class CardDeletionTransaction extends Transaction {
    private String cardNumber;
    private String cardHolder;
    private String account;

    public CardDeletionTransaction(int timestamp, String cardNumber, String cardHolder, String account) {
        super(timestamp);
        this.description = "The card has been destroyed";
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.account = account;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getAccount() {
        return account;
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }

}
