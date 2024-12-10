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

    public void createCard(String accountIBAN, String cardType, String email) {
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
            /*Transaction errorTransaction = new ErrorTransaction(
                    (int) (System.currentTimeMillis() / 1000),
                    "Unauthorized card creation attempt",
                    0.0,
                    "failure",
                    "User with email " + email + " does not own the account with IBAN " + accountIBAN
            );

            account.addTransaction(errorTransaction);

             */
            return;
        }

        User user = userService.getUserByEmail(email);
        String cardNumber = Utils.generateCardNumber();
        Card card = null;
        if (cardType == "regularCard") {
            card = new RegularCard(cardNumber, account, user);
        } else if (cardType == "oneTimeCard") {
            card = new OneTimePayCard(cardNumber, account, user);
        }
        //Card card = new RegularCard(cardNumber, account, user);
        cardsByNumber.put(cardNumber, card);
        account.addCard(card);

        /*Transaction txn = new CardCreationTransaction(
                (int)(System.currentTimeMillis()/1000),
                "Card Created",
                0.0,
                "success",
                cardType,
                cardNumber
        );

        account.addTransaction(txn);

         */
        //return card;
    }

    public void deleteCard(String cardNumber, String email) {
        Card card = cardsByNumber.get(cardNumber);
        if (card == null) {
            /*Transaction errorTransaction = new ErrorTransaction(
                    (int) (System.currentTimeMillis() / 1000),
                    "Card deletion failed",
                    0.0,
                    "failure",
                    "Card with number " + cardNumber + " does not exist."
            );

             */
            return;
        }

        if (!card.getOwner().getEmail().equals(email)) {
            /*Transaction errorTransaction = new ErrorTransaction(
                    (int) (System.currentTimeMillis() / 1000),
                    "Unauthorized card deletion attempt",
                    0.0,
                    "failure",
                    "User with email " + email + " does not own the card with number " + cardNumber
            );
            card.getAccount().addTransaction(errorTransaction);

             */
            return;
        }

        cardsByNumber.remove(cardNumber);
        card.getAccount().getCards().remove(card);

        /*Transaction txn = new CardDeletionTransaction(
                (int)(System.currentTimeMillis()/1000),
                "Card Deleted",
                0.0,
                "success",
                cardNumber
        );

        card.getAccount().addTransaction(txn);

         */
    }

    public String payOnline(String cardNumber, double amount, String currency, String email) {
        Card card = cardsByNumber.get(cardNumber);

        if (card == null) {
            return "Card not found";
        }

        if (!card.getOwner().getEmail().equals(email)) {
            return "Error: Unauthorized access to card.";
        }

        if (!card.getAccount().getCurrency().equals(currency)) {
            amount = exchangeService.convertCurrency(currency, card.getAccount().getCurrency(), amount);
        }

        if (card.getAccount().getBalance() < amount) {
            return "Error: Insufficient funds.";
        }

        card.makePayment(amount);
        /*Transaction transaction = new PaymentTransaction(
                timestamp,
                description,
                amount,
                "success",
                commerciant
        );

        card.getAccount().addTransaction(transaction);

         */
        return "Success";
    }


    public Map<String, Card> getCardsByNumber() {
        return cardsByNumber;
    }
}
