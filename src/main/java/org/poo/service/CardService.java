package org.poo.service;

import org.poo.model.account.Account;
import org.poo.model.card.Card;
import org.poo.model.card.RegularCard;
import org.poo.model.transaction.CardCreationTransaction;
import org.poo.model.transaction.ErrorTransaction;
import org.poo.model.transaction.Transaction;
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
            //System.err.println("Error: User does not own this account");

            // Crearea unei tranzacții de tip ErrorTransaction pentru acces neautorizat
            Transaction errorTransaction = new ErrorTransaction(
                    (int) (System.currentTimeMillis() / 1000),
                    "Unauthorized card creation attempt",
                    0.0,
                    "failure",
                    "User with email " + email + " does not own the account with IBAN " + accountIBAN
            );

            account.addTransaction(errorTransaction);
            return;
            //return null;
        }

        User user = userService.getUserByEmail(email);
        String cardNumber = Utils.generateCardNumber();
        Card card = new RegularCard(cardNumber, account, user);
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

    public Map<String, Card> getCardsByNumber() {
        return cardsByNumber;
    }
}
