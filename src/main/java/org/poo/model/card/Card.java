package org.poo.model.card;

import org.poo.model.account.Account;
import org.poo.model.user.User;

import java.util.Map;

public abstract class Card {
    protected String cardNumber;
    protected final Account account;
    protected final User owner;
    protected boolean isBlocked;

    public Card(final String cardNumber, final Account account, final User owner) {
        this.cardNumber = cardNumber;
        this.account = account;
        this.owner = owner;
        this.isBlocked = false;
    }

    /**
     *
     * @return
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     *
     * @return
     */
    public Account getAccount() {
        return account;
    }

    /**
     *
     * @return
     */
    public User getOwner() {
        return owner;
    }

    /**
     *
     * @return
     */
    public boolean isBlocked() {
        return isBlocked;
    }

    /**
     *
     */
    public void block() {
        isBlocked = true;
    }

    /**
     *
     */
    public void unblock() {
        isBlocked = false;
    }

    /**
     *
     * @return
     */
    public String checkStatus() {
        if (isBlocked) {
            return "frozen";
        }
        return "active";
    }

    /**
     *
     * @param cardNumber
     */
    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     *
     * @param amount
     * @param cardsByNumber
     * @return
     */
    public abstract String makePayment(double amount, Map<String, Card> cardsByNumber);
}
