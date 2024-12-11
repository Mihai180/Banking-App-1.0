package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class CardCreationTransaction extends Transaction {
    private String cardNumber;
    private String cardHolder;
    private String account;

    public CardCreationTransaction(int timestamp, String cardHolder, String account, String cardNumber) {
        super(timestamp);
        this.description = "New card created";
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
