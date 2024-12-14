package org.poo.model.card;

import org.poo.model.account.Account;
import org.poo.model.user.User;

import java.util.Map;

public class RegularCard extends Card {
    public RegularCard(String cardNumber, Account account, User owner) {
        super(cardNumber, account, owner);
    }

    @Override
    public String makePayment(double amount, Map<String, Card> cardsByNumber) {
        if (isBlocked) {
            return "You can't pay this amount because isBlocked";
        }
        account.withdraw(amount);
        return "Success";
    }

    /*public void accept(CardVisitor visitor) {
        visitor.visit(this);
    }

     */
}
