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
    public String makePayment(double amount) {
        if (isBlocked) {
            return  "You can't pay this amount because isBlocked";
        }
        if (isUsed) {
            return "You can't pay this amount because is used";
        }
        account.withdraw(amount);
        /*if (result.equals("Success")) {
            //isUsed = true;
            setCardNumber(Utils.generateCardNumber());
            return "New card generated successfully";
        }

         */
        setCardNumber(Utils.generateCardNumber());
        isUsed = true;
        return "Success";
    }

    public boolean isUsed() {
        return isUsed;
    }

    public String checkCardStatus() {
        if (isBlocked) {
            return "frozen";
        }
        if (isUsed) {
            return "used";
        }
        return "active";
    }

    /*public void accept(CardVisitor visitor) {
        visitor.visit(this);
    }

     */
}
