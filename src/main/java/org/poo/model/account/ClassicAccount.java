package org.poo.model.account;

import org.poo.model.user.User;

public final class ClassicAccount extends Account {
    public ClassicAccount(final String iban, final User owner, final String currency) {
        super(iban, owner, currency);
    }

    @Override
    public String getAccountType() {
        return "classic";
    }

    @Override
    public void changeInterestRate(final double newInterestRate) {

    }

    @Override
    public void addInterest() {

    }
}
