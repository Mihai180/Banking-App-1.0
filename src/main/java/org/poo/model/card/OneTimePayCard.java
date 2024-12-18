package org.poo.model.card;

import org.poo.model.account.Account;
import org.poo.model.user.User;
import org.poo.utils.Utils;
import java.util.Map;

/**
 * Clasa finală OneTimePayCard reprezintă tipul specific de card cu care se
 * poate face o singură plată
 */
public final class OneTimePayCard extends Card {
    private boolean isUsed;

    public OneTimePayCard(final String cardNumber, final Account account, final User owner) {
        super(cardNumber, account, owner);
        this.isUsed = false;
    }

    /**
     * Efectuează o plată folosind cardul OneTimePayCard
     * Verifică dacă cardul este blocat sau deja utilizat. Dacă nu, efectuează retragerea sumei
     * din cont, marchează cardul ca fiind utilizat și generează un nou număr de card.
     * @param amount este suma de plată
     * @param cardsByNumber este un map de carduri indexate după numărul cardului
     * @return "Success", "You can't pay this amount because isBlocked",
     * sau "You can't pay this amount because is used"
     */
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
     * Verifică starea cardului OneTimePayCard
     * @return starea curentă a cardului ("frozen", "used" sau "active")
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

    @Override
    public void setIsUsed(final boolean isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    public String getCardType() {
        return "OneTimePayCard";
    }
}
