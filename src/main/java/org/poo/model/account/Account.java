package org.poo.model.account;

import org.poo.model.card.Card;
import org.poo.model.transaction.Transaction;
import org.poo.model.user.User;

import java.util.ArrayList;

/**
 * Clasa abstractă Account reprezintă o bază comună pentru tipurile de conturi bancare
 */
public abstract class Account {
    protected String iban;
    protected double balance;
    protected String currency;
    protected ArrayList<Transaction> transactions;
    protected User owner;
    protected Double minimumBalance;
    protected ArrayList<Card> cards;

    /**
     * Constructorul inițializează contul cu IBAN, proprietar și monedă.
     * Soldul inițial este 0, nu există o limită minimă la crearea contului,
     * iar listele pentru tranzacții și carduri sunt goale
     * */
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
     * @return IBAN-ul contului
     */
    public String getIban() {
        return iban;
    }

    /**
     * @return Soldul curent al contului
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @return Moneda contului
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @return Lista de tranzacții ale contului
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * @return Proprietarul contului
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @return Balanța minimă a contului
     */
    public Double getMinimumBalance() {
        return minimumBalance;
    }

    /**
     * @return Lista de carduri ale contului
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Setează soldul minim permis al contului
     * @param minimumBalance este noua balanță minimă a contului
     */
    public void setMinimumBalance(final Double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    /**
     * Depune o sumă de bani în cont
     * @param amount este suma de depus
     */
    public void deposit(final Double amount) {
        this.balance += amount;
    }

    /**
     * Retrage o sumă de bani din cont dacă soldul este suficient
     * @param amount este suma de retras
     * @return "Success" dacă operațiunea a reușit, altfel "Insufficient funds"
     */
    public String withdraw(final Double amount) {
        if (balance - amount < 0) {
            return "Insufficient funds";
        }
        this.balance -= amount;
        return "Success";
    }

    /**
     * Adaugă o tranzacție la lista tranzacțiilor acestui cont
     * @param transaction este tranzacția de adăugat
     */
    public void addTransaction(final Transaction transaction) {
        this.transactions.add(transaction);
    }

    /**
     * Adaugă un card la lista de carduri asociate contului
     * @param card este cardul de adăugat
     */
    public void addCard(final Card card) {
        this.cards.add(card);
    }

    /**
     * @return Tipul contului, pentru clasa de bază, returnează null.
     */
    public String getAccountType() {
        return null;
    }

    /**
     * Metodă abstractă pentru schimbarea ratei dobânzii
     * @param newInterestRate este noua rată a dobânzii
     */
    public abstract void changeInterestRate(double newInterestRate);

    /**
     * Metodă abstractă pentru aplicarea dobânzii în cont
     */
    public abstract void addInterest();
}
