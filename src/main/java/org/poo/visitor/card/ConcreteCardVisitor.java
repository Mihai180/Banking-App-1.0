package org.poo.visitor.card;

import org.poo.model.card.OneTimePayCard;
import org.poo.model.card.RegularCard;

public class ConcreteCardVisitor implements CardVisitor {
    private String cardInfo;
    private String cardStatus;

    public String getCardInfo() {
        return cardInfo;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    @Override
    public void visit(RegularCard card) {
        // Collect card information
        cardInfo = "RegularCard Number: " + card.getCardNumber()
                + ", Associated Account IBAN: " + card.getAccount().getIban();

        // Check card status
        cardStatus = card.checkStatus();
    }

    @Override
    public void visit(OneTimePayCard card) {
        // Collect card information
        cardInfo = "OneTimePayCard Number: " + card.getCardNumber()
                + ", Associated Account IBAN: " + card.getAccount().getIban();

        // Check card status
        cardStatus = card.checkStatus();
    }
}
