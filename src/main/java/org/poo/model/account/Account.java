package org.poo.model.account;

import org.poo.model.card.Card;
import org.poo.model.transaction.Transaction;
import org.poo.model.user.User;
import org.poo.visitor.account.AccountVisitor;

import java.util.ArrayList;

public abstract class Account {
    protected String iban;
    protected double balance;
    protected String currency;
    protected ArrayList<Transaction> transactions;
    protected User owner;
    protected Double minimumBalance;
    protected ArrayList<Card> cards;

    public Account(String iban, User owner, String currency) {
        this.iban = iban;
        this.owner = owner;
        this.currency = currency;
        this.transactions = new ArrayList<>();
        this.minimumBalance = null;
        this.cards = new ArrayList<>();
        this.balance = 0.0;
    }

    public String getIban() {
        return iban;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public User getOwner() {
        return owner;
    }

    public Double getMinimumBalance() {
        return minimumBalance;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setMinimumBalance(Double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public void deposit(Double amount) {
        this.balance += amount;
    }

    public void withdraw(Double amount) throws Exception {
        if (balance - amount < 0) {
            throw new Exception("Insufficient funds");
        }
        this.balance -= amount;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public String getAccountType() {
        return null;
    }

    public abstract void accept(AccountVisitor visitor);


}
