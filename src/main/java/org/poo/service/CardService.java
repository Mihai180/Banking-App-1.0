package org.poo.service;

import org.poo.exception.AccountNotFoundException;
import org.poo.exception.CardNotFoundException;
import org.poo.exception.FrozenCardException;
import org.poo.exception.UnauthorizedAccessException;
import org.poo.exception.InsufficientFundsException;
import org.poo.exception.CardIsUsedException;
import org.poo.model.account.Account;
import org.poo.model.card.Card;
import org.poo.model.card.OneTimePayCard;
import org.poo.model.card.RegularCard;
import org.poo.model.user.User;
import org.poo.utils.Utils;
import java.util.HashMap;
import java.util.Map;

/**
 * Clasa finală CardService gestionează operațiunile legate de carduri
 */
public final class CardService {
    private Map<String, Card> cardsByNumber = new HashMap<>();
    private UserService userService;
    private AccountService accountService;
    private ExchangeService exchangeService;

    public CardService(final UserService userService,
                       final AccountService accountService,
                       final ExchangeService exchangeService) {
        this.userService = userService;
        this.accountService = accountService;
        this.exchangeService = exchangeService;
    }

    /**
     * Golește toate cardurile gestionate de serviciu
     */
    public void clear() {
        cardsByNumber.clear();
    }

    /**
     * Creează un card nou pentru un cont specificat
     * @param accountIBAN este IBAN-ul contului pentru care se creează cardul
     * @param cardType este tipul cardului
     * @param email este adresa de email a utilizatorului asociat cardului
     * @return cardul creat sau null dacă utilizatorul nu este autorizat
     * @throws AccountNotFoundException dacă contul cu IBAN-ul specificat nu este găsit
     */
    public Card createCard(final String accountIBAN, final String cardType, final String email) {
        Account account = accountService.getAccountByIBAN(accountIBAN);

        if (account == null) {
            throw new AccountNotFoundException("Account not found with IBAN: " + accountIBAN);
        }

        if (!account.getOwner().getEmail().equals(email)) {
            return null;
        }

        User user = userService.getUserByEmail(email);
        String cardNumber = Utils.generateCardNumber();
        Card card = null;
        if (cardType == "regularCard") {
            card = new RegularCard(cardNumber, account, user);
        } else if (cardType == "oneTimeCard") {
            card = new OneTimePayCard(cardNumber, account, user);
        }
        cardsByNumber.put(cardNumber, card);
        account.addCard(card);

        return card;
    }

    /**
     * Șterge un card asociat unui utilizator
     * @param cardNumber este numărul cardului care trebuie șters
     * @param email este adresa de email a utilizatorului asociat cardului
     */
    public void deleteCard(final String cardNumber, final String email) {
        Card card = cardsByNumber.get(cardNumber);
        if (card == null) {
            return;
        }

        if (!card.getOwner().getEmail().equals(email)) {
            return;
        }

        cardsByNumber.remove(cardNumber);
        card.getAccount().getCards().remove(card);
    }

    /**
     * Efectuează o plată online folosind un card
     * @param cardNumber este cardul utilizat pentru plată
     * @param amount este suma de plată
     * @param currency este moneda în care este exprimată suma de plată
     * @param email este adresa de email a utilizatorului asociat cardului
     * @return mesaj de succes sau detalierea erorii
     * @throws CardNotFoundException dacă cardul nu este găsit
     * @throws FrozenCardException dacă cardul este blocat
     * @throws UnauthorizedAccessException dacă utilizatorul nu este autorizat să utilizeze cardul
     * @throws InsufficientFundsException dacă contul asociat cardului nu are fonduri suficiente
     * @throws CardIsUsedException dacă cardul a fost deja utilizat și nu mai poate fi folosit
     */
    public String payOnline(final String cardNumber, final double amount, final String currency,
                            final String email) {
        Card card = cardsByNumber.get(cardNumber);

        if (card == null) {
            throw new CardNotFoundException("Card not found");
        }

        if (card.isBlocked()) {
            throw new FrozenCardException("Card is frozen");
        }

        if (!card.getOwner().getEmail().equals(email)) {
            throw new UnauthorizedAccessException("Unauthorized access to card");
        }

        double finalAmount = amount;
        if (!card.getAccount().getCurrency().equals(currency)) {
            finalAmount = exchangeService.convertCurrency(currency,
                    card.getAccount().getCurrency(), amount);
        }

        if (card.getAccount().getBalance() < finalAmount) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        String result = card.makePayment(finalAmount, cardsByNumber);

        if (result.equals("You can't pay this amount because is used")) {
            throw new CardIsUsedException("You can't pay this amount because is used");
        }

        // Pentru un card one time pay dupa ce i-am generat un nou numar după o plată cu succes
        // setez noul card ca fiind nefolosit
        if (result.equals("Success") && card.getCardType().equals("OneTimePayCard")) {
            String newCardNumber = card.getCardNumber();
            Card newCard = cardsByNumber.get(newCardNumber);

            if (newCard != null && newCard != card) {
                newCard.setIsUsed(false);
            }
            return "New card generated successfully: " + newCardNumber;
        }

        // Daca balanța minimă este null înseamnă că plata s-a efectuat cu succes
        // deoarece contul este clasic
        Double minBalance = card.getAccount().getMinimumBalance();
        if (minBalance == null) {
            return "Success";
        }

        return "Success";
    }

    /**
     * Obține un card după numărul acestuia
     * @param cardNumber este numărul cardului
     * @return cardul asociat numărului sau null dacă nu este găsit
     */
    public Card getCardByNumber(final String cardNumber) {
        return cardsByNumber.get(cardNumber);
    }

    /**
     * Verifică statusul unui card specificat
     * @param cardNumber este numărul cardului al cărui status se verifică
     * @return statusul cardului sau un mesaj dacă cardul nu este găsit
     */
    public String checkCardStatus(final String cardNumber) {
        // Iterez prin toți utilizatorii pentru a găsi cardul
        for (User user : userService.getAllUsers().values()) {
            for (Account account : user.getAccounts()) {
                for (Card actualcard : account.getCards()) {
                    if (actualcard.getCardNumber().equals(cardNumber)) {
                        Card card = actualcard;
                        if (card.getAccount().getBalance() == 0) {
                            return "Insufficient funds";
                        }
                        return card.checkStatus();
                    }
                }
            }
        }
        return "Card not found";
    }
}
