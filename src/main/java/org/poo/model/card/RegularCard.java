package org.poo.model.card;

import org.poo.model.account.Account;
import org.poo.model.user.User;
import java.util.Map;

/**
 * Clasa finală RegularCard reprezintă un tip standard de card
 * Acest tip de card poate fi utilizat pentru multiple plăți și tranzacții,
 * fără restricții privind numărul de utilizări
 */
public final class RegularCard extends Card {
    public RegularCard(final String cardNumber, final Account account, final User owner) {
        super(cardNumber, account, owner);
    }

    /**
     * Metoda este goală deoarece cu acest tip de card se pot face oricâte plăți se dorește
     * @param isUsed true dacă cardul este marcat ca utilizat, false altfel (nu este folosit)
     */
    @Override
    public void setIsUsed(final boolean isUsed) {

    }

    /**
     * Efectuează o plată folosind cardul RegularCard
     * Verifică dacă cardul este blocat; dacă nu, retrage suma specificată din contul asociat.
     * @param amount este suma de plată
     * @param cardsByNumber este un map de carduri indexate după numărul cardului
     * @return "Success" sau "You can't pay this amount because isBlocked"
     */
    @Override
    public String makePayment(final double amount, final Map<String, Card> cardsByNumber) {
        if (isBlocked) {
            return "You can't pay this amount because isBlocked";
        }
        account.withdraw(amount);
        return "Success";
    }

    @Override
    public String getCardType() {
        return "Regular";
    }
}
