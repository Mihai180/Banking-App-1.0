package org.poo.model.card;

import org.poo.model.account.Account;
import org.poo.model.user.User;
import java.util.Map;

public final class RegularCard extends Card {
    public RegularCard(final String cardNumber, final Account account, final User owner) {
        super(cardNumber, account, owner);
    }

    @Override
    public String makePayment(final double amount, final Map<String, Card> cardsByNumber) {
        if (isBlocked) {
            return "You can't pay this amount because isBlocked";
        }
        account.withdraw(amount);
        return "Success";
    }
}
