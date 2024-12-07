package org.poo.model.card;

import org.poo.model.account.Account;
import org.poo.model.user.User;

public class OneTimePayCard extends Card {
    private boolean isUsed;

    public OneTimePayCard(String cardNumber, Account account, User owner) {
        super(cardNumber, account, owner);
        this.isUsed = false;
    }

    @Override
    public void makePayment(double amount) throws Exception {
        if (isBlocked) {
            throw new Exception("Card is blocked");
        }
        if (isUsed) {
            throw new Exception("This card has been used!");
        }
        account.withdraw(amount);
        isUsed = true;
    }

    /*public void accept(CardVisitor visitor) {
        visitor.visit(this);
    }

     */
}
