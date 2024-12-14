package org.poo.model.card;

import org.poo.model.account.Account;
import org.poo.model.user.User;
import org.poo.service.CardService;
import org.poo.utils.Utils;

import java.util.Map;

public class OneTimePayCard extends Card {
    private boolean isUsed;

    public OneTimePayCard(String cardNumber, Account account, User owner) {
        super(cardNumber, account, owner);
        this.isUsed = false;
    }

    @Override
    public String makePayment(double amount, Map<String, Card> cardsByNumber) {
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
        isUsed = true;
        //String oldCardNumber = getCardNumber();
        //System.out.println("Old Card Number: " + this.getCardNumber());
        //cardsByNumber.remove(this.getCardNumber());
        //System.out.println("Card removed from map. Current map: " + cardsByNumber.keySet());
        setCardNumber(Utils.generateCardNumber());
        //System.out.println("New Card Number: " + this.getCardNumber());

        //cardsByNumber.remove(oldCardNumber);
        //cardsByNumber.put(getCardNumber(), this);
        //cardsByNumber.put(this.getCardNumber(), this);
        //System.out.println("Card added to map. Updated map: " + cardsByNumber.keySet());
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

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    /*public void accept(CardVisitor visitor) {
        visitor.visit(this);
    }

     */
}
