package org.poo.model.account;

import org.poo.model.user.User;

/**
 * Clasa finală SavingsAccount reprezintă un tip de cont de economii,
 * extinzând clasa de bază Account
 */
public final class SavingsAccount extends Account {
    private double interestRate;
    public SavingsAccount(final String iban, final User owner, final String currency,
                          final double interestRate) {
        super(iban, owner, currency);
        this.interestRate = interestRate;
    }

    /**
     * Adaugă dobânda la soldul contului. Soldul crește cu sold * rata_dobânzii
     */
    @Override
    public void addInterest() {
        balance += balance * interestRate;
    }

    /**
     * Modifică rata dobânzii asociată contului
     * @param newInterestRate este noua rată a dobânzii
     */
    @Override
    public void changeInterestRate(final double newInterestRate) {
        this.interestRate = newInterestRate;
    }

    /**
     * Returnează tipul contului ca fiind "savings"
     * @return "savings"
     */
    @Override
    public String getAccountType() {
        return "savings";
    }
}
