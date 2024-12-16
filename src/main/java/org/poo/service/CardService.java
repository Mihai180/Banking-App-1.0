package org.poo.service;

import org.poo.model.account.Account;
import org.poo.model.card.Card;
import org.poo.model.card.OneTimePayCard;
import org.poo.model.card.RegularCard;
import org.poo.model.user.User;
import org.poo.utils.Utils;

import java.util.HashMap;
import java.util.Map;

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
     *
     */
    public void clear() {
        cardsByNumber.clear();
    }

    /**
     *
     * @param accountIBAN
     * @param cardType
     * @param email
     * @return
     */
    public Card createCard(final String accountIBAN, final String cardType, final String email) {
        Account account = accountService.getAccountByIBAN(accountIBAN);

        if (account == null) {
            throw new IllegalArgumentException("Account not found with IBAN: " + accountIBAN);
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
     *
     * @param cardNumber
     * @param email
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
     *
     * @param cardNumber
     * @param amount
     * @param currency
     * @param email
     * @return
     */
    public String payOnline(final String cardNumber, final double amount, final String currency,
                            final String email) {
        Card card = cardsByNumber.get(cardNumber);

        if (card == null) {
            return "Card not found";
        }

        if (card.isBlocked()) {
            return "Card is frozen";
        }

        if (!card.getOwner().getEmail().equals(email)) {
            return "Unauthorized access to card";
        }

        double finalAmount = amount;
        if (!card.getAccount().getCurrency().equals(currency)) {
            finalAmount = exchangeService.convertCurrency(currency,
                    card.getAccount().getCurrency(), amount);
        }

        if (card.getAccount().getBalance() < finalAmount) {
            return "Insufficient funds";
        }

        String result = card.makePayment(finalAmount, cardsByNumber);

        if (result.equals("You can't pay this amount because is used")) {
            return result;
        }

        if (result.equals("Success") && card instanceof OneTimePayCard) {
            String newCardNumber = card.getCardNumber();
            Card newCard = cardsByNumber.get(newCardNumber);

            if (newCard != null && newCard != card) {
                ((OneTimePayCard) newCard).setIsUsed(false);
            }
            return "New card generated successfully: " + newCardNumber;
        }

        Double minBalance = card.getAccount().getMinimumBalance();

        if (minBalance == null) {
            return "Success";
        }

        return "Success";
    }

    /**
     *
     * @param cardNumber
     * @return
     */
    public Card getCardByNumber(final String cardNumber) {
        return cardsByNumber.get(cardNumber);
    }

    /**
     *
     * @param cardNumber
     * @return
     */
    public String checkCardStatus(final String cardNumber) {
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
