package org.poo.model.card;

import org.poo.model.account.Account;
import org.poo.model.user.User;
import org.poo.utils.Utils;

import java.util.Map;

public final class OneTimePayCard extends Card {
    private boolean isUsed;

    public OneTimePayCard(final String cardNumber, final Account account, final User owner) {
        super(cardNumber, account, owner);
        this.isUsed = false;
    }

    @Override
    public String makePayment(final double amount, final Map<String, Card> cardsByNumber) {
        if (isBlocked) {
            return  "You can't pay this amount because isBlocked";
        }
        if (isUsed) {
            return "You can't pay this amount because is used";
        }
        account.withdraw(amount);
        isUsed = true;
        setCardNumber(Utils.generateCardNumber());
        return "Success";
    }

    /**
     *
     * @return
     */
    public String checkCardStatus() {
        if (isBlocked) {
            return "frozen";
        }
        if (isUsed) {
            return "used";
        }
        return "active";
    }

    public void setIsUsed(final boolean isUsed) {
        this.isUsed = isUsed;
    }
}
