package org.poo.model.account;

import org.poo.model.user.User;

/**
 * Clasa finală ClassicAccount reprezintă un tip specific de cont,
 * care extinde clasa abstractă Account
 */
public final class ClassicAccount extends Account {
    public ClassicAccount(final String iban, final User owner, final String currency) {
        super(iban, owner, currency);
    }

    /**
     * Returnează tipul contului ca fiind "classic"
     * @return "classic"
     */
    @Override
    public String getAccountType() {
        return "classic";
    }

    /**
     * Metoda este goală deoarece nu este nevoie de aceasta operațiune la un cont classic
     * @param newInterestRate este noua rată a dobânzii
     */
    @Override
    public void changeInterestRate(final double newInterestRate) {

    }

    /**
     * Metoda este goală deoarece nu este nevoie de aceasta operațiune la un cont classic
     */
    @Override
    public void addInterest() {

    }
}
