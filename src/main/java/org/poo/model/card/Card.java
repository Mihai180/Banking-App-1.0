package org.poo.model.card;

import org.poo.model.account.Account;
import org.poo.model.user.User;
import java.util.Map;

/**
 * Clasa abstractă Card reprezintă un card asociat unui cont bancar
 */
public abstract class Card {
    protected String cardNumber;
    protected final Account account;
    protected final User owner;
    protected boolean isBlocked;

    public Card(final String cardNumber, final Account account, final User owner) {
        this.cardNumber = cardNumber;
        this.account = account;
        this.owner = owner;
        this.isBlocked = false;
    }

    /**
     * Returnează numărul cardului
     * @return numărul cardului
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Returnează contul asociat cardului
     * @return contul cardului
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Returnează proprietarul cardului
     * @return proprietarul cardului
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Verifică dacă cardul este blocat
     * @return true dacă este blocat, false dacă este activ
     */
    public boolean isBlocked() {
        return isBlocked;
    }

    /**
     * Blochează cardul
     */
    public void block() {
        isBlocked = true;
    }

    /**
     * Verifică starea cardului, returnând "frozen" dacă este blocat și "active" dacă este activ
     * @return "frozen" sau "active"
     */
    public String checkStatus() {
        if (isBlocked) {
            return "frozen";
        }
        return "active";
    }

    /**
     * Setează un nou număr pentru card
     * @param cardNumber este noul număr al cardului
     */
    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Efectuează o plată cu cardul
     * @param amount este suma de plată
     * @param cardsByNumber este un map de carduri indexate după numărul cardului
     * @return mesajul pentru plată
     */
    public abstract String makePayment(double amount, Map<String, Card> cardsByNumber);

    /**
     * Setează dacă cardul a fost utilizat sau nu
     * @param isUsed true dacă cardul este marcat ca utilizat, false altfel
     */
    public abstract void setIsUsed(boolean isUsed);

    /**
     * Returnează tipul cardului, care va fi definit de clasele derivate
     * @return Tipul cardului
     */
    public abstract String getCardType();
}
