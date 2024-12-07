package org.poo.model.account;

import org.poo.model.user.User;
import org.poo.visitor.account.AccountVisitor;

public class SavingsAccount extends Account {
    private double interestRate;
    public SavingsAccount(String iban, User owner, String currency, double interestRate) {
        super(iban, owner, currency);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void addInterest() {
        balance += balance * interestRate;
    }

    public void changeInterestRate(double newInterestRate) {
        this.interestRate = newInterestRate;
    }

    public String getAccountType() {
        return "savings";
    }

    public void accept(AccountVisitor visitor) {
        visitor.visit(this);
    }
}
