package org.poo.service;

import org.poo.exception.CardNotFoundException;
import org.poo.model.account.Account;
import org.poo.model.card.Card;
import org.poo.model.card.OneTimePayCard;
import org.poo.model.card.RegularCard;
import org.poo.model.transaction.*;
import org.poo.model.user.User;
import org.poo.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class CardService {
    private Map<String, Card> cardsByNumber = new HashMap<>();
    private UserService userService;
    private AccountService accountService;
    private MerchantService merchantService;
    private ExchangeService exchangeService;

    public CardService(UserService userService,
                       AccountService accountService,
                       MerchantService merchantService,
                       ExchangeService exchangeService) {
        this.userService = userService;
        this.accountService = accountService;
        this.merchantService = merchantService;
        this.exchangeService = exchangeService;
    }

    public void clear() {
        cardsByNumber.clear();
    }

    public Card createCard(String accountIBAN, String cardType, String email) {
        /*if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }

         */

        Account account = accountService.getAccountByIBAN(accountIBAN);

        if (account == null) {
            throw new IllegalArgumentException("Account not found with IBAN: " + accountIBAN);
        }

        /*if (!account.getOwner().getEmail().equals(email)) {
            throw new IllegalArgumentException("User does not own this account");
        }

         */

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

    public void deleteCard(String cardNumber, String email) {
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

    public String payOnline(String cardNumber, double amount, String currency, String email) {
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

        if (!card.getAccount().getCurrency().equals(currency)) {
            amount = exchangeService.convertCurrency(currency, card.getAccount().getCurrency(), amount);
        }

        if (card.getAccount().getBalance() < amount) {
            return "Insufficient funds";
        }

        card.makePayment(amount);

        double balance = card.getAccount().getBalance();
        Double minBalance = card.getAccount().getMinimumBalance();

        if (minBalance == null) {
            return "Success";
        }

        if (balance <= minBalance) {
            card.block();
            return "frozen";
        }

        if ((minBalance - balance) <= 30) {
            return "warning";
        }

        return "Success";
    }


    public Map<String, Card> getCardsByNumber() {
        return cardsByNumber;
    }

    public Card getCardByNumber(String cardNumber) {
        return cardsByNumber.get(cardNumber);
    }

    public String checkCardStatus(String cardNumber) {
        Card card = cardsByNumber.get(cardNumber);
        if (card == null) {
            return "Card not found";
        }
        if (card.getAccount().getBalance() == 0) {
            return "Insufficient funds";
        }
        return card.checkStatus();
    }
}
