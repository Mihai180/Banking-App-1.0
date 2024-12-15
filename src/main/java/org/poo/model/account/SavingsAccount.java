package org.poo.model.account;

import org.poo.model.user.User;

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

    @Override
    public String getAccountType() {
        return "savings";
    }
}
