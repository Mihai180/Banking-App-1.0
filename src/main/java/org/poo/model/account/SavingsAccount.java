package org.poo.model.account;

import org.poo.model.user.User;

public final class SavingsAccount extends Account {
    private double interestRate;
    public SavingsAccount(final String iban, final User owner, final String currency,
                          final double interestRate) {
        super(iban, owner, currency);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(final double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     *
     */
    public void addInterest() {
        balance += balance * interestRate;
    }

    /**
     *
     * @param newInterestRate
     */
    public void changeInterestRate(final double newInterestRate) {
        this.interestRate = newInterestRate;
    }

    @Override
    public String getAccountType() {
        return "savings";
    }
}
