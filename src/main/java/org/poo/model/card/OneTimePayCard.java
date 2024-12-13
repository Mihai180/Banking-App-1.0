package org.poo.model.card;

import org.poo.model.account.Account;
import org.poo.model.user.User;
import org.poo.utils.Utils;

public class OneTimePayCard extends Card {
    private boolean isUsed;

    public OneTimePayCard(String cardNumber, Account account, User owner) {
        super(cardNumber, account, owner);
        this.isUsed = false;
    }

    @Override
    public void makePayment(double amount) {
        if (isBlocked) {
            System.out.println("You can't pay this amount because isBlocked");
        }
        if (isUsed) {
            System.out.println("You can't pay this amount because is used");
        }
        account.withdraw(amount);
        isUsed = true;
        setCardNumber(Utils.generateCardNumber());
    }

    public boolean isUsed() {
        return isUsed;
    }

    /*public void accept(CardVisitor visitor) {
        visitor.visit(this);
    }

     */
}
