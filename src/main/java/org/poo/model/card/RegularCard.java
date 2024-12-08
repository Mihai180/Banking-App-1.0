package org.poo.model.card;

import org.poo.model.account.Account;
import org.poo.model.user.User;

public class RegularCard extends Card {
    public RegularCard(String cardNumber, Account account, User owner) {
        super(cardNumber, account, owner);
    }

    @Override
    public void makePayment(double amount) {
        if (isBlocked) {
            System.out.println("You can't pay this amount because isBlocked");
        }
        account.withdraw(amount);
    }

    /*public void accept(CardVisitor visitor) {
        visitor.visit(this);
    }

     */
}
