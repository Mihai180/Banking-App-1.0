package org.poo.model.account;

import org.poo.model.card.Card;
import org.poo.model.transaction.Transaction;
import org.poo.model.user.User;

import java.util.ArrayList;

public abstract class Account {
    protected String iban;
    protected double balance;
    protected String currency;
    protected ArrayList<Transaction> transactions;
    protected User owner;
    protected Double minimumBalance;
    protected ArrayList<Card> cards;

    public Account(final String iban, final User owner, final String currency) {
        this.iban = iban;
        this.owner = owner;
        this.currency = currency;
        this.transactions = new ArrayList<>();
        this.minimumBalance = null;
        this.cards = new ArrayList<>();
        this.balance = 0.0;
    }

    /**
     *
     * @return
     */
    public String getIban() {
        return iban;
    }

    /**
     *
     * @return
     */
    public double getBalance() {
        return balance;
    }

    /**
     *
     * @return
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @return
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
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
    public Double getMinimumBalance() {
        return minimumBalance;
    }

    /**
     *
     * @return
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     *
     * @param minimumBalance
     */
    public void setMinimumBalance(final Double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    /**
     *
     * @param amount
     */
    public void deposit(final Double amount) {
        this.balance += amount;
    }

    /**
     *
     * @param amount
     * @return
     */
    public String withdraw(final Double amount) {
        if (balance - amount < 0) {
            return "Insufficient funds";
        }
        this.balance -= amount;
        return "Success";
    }

    /**
     *
     * @param transaction
     */
    public void addTransaction(final Transaction transaction) {
        this.transactions.add(transaction);
    }

    /**
     *
     * @param card
     */
    public void addCard(final Card card) {
        this.cards.add(card);
    }

    /**
     *
     * @return
     */
    public String getAccountType() {
        return null;
    }

    /**
     *
     * @param newInterestRate
     */
    public abstract void changeInterestRate(double newInterestRate);

    /**
     *
     */
    public abstract void addInterest();
}
