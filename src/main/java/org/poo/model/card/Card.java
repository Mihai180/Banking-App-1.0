package org.poo.model.card;

import org.poo.model.account.Account;
import org.poo.model.user.User;

public abstract class Card {
    protected String cardNumber;
    protected Account account;
    protected User owner;
    protected boolean isBlocked;

    public Card(String cardNumber, Account account, User owner) {
        this.cardNumber = cardNumber;
        this.account = account;
        this.owner = owner;
        this.isBlocked = false;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Account getAccount() {
        return account;
    }

    public User getOwner() {
        return owner;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void block() {
        isBlocked = true;
    }
    public void unblock() {
        isBlocked = false;
    }

    public String checkStatus() {
        if (isBlocked) {
            return "Blocked";
        }
        return "active";
    }

    public abstract void makePayment(double amount) throws Exception;

    //public abstract void accept(CardVisitor visitor);
}
