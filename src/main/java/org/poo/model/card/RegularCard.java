package org.poo.model.card;

import org.poo.model.account.Account;
import org.poo.model.user.User;

public class RegularCard extends Card {
    public RegularCard(String cardNumber, Account account, User owner) {
        super(cardNumber, account, owner);
    }

    @Override
    public void makePayment(double amount) throws Exception {
        if (isBlocked) {
            throw new Exception("Card is blocked");
        }
        account.withdraw(amount);
    }

    /*public void accept(CardVisitor visitor) {
        visitor.visit(this);
    }

     */
}
