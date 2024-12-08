package org.poo.service;

import org.poo.exception.AccountNotFoundException;
import org.poo.exception.UserNotFoundException;
import org.poo.model.account.Account;
import org.poo.model.account.ClassicAccount;
import org.poo.model.account.SavingsAccount;
import org.poo.model.card.Card;
import org.poo.model.card.RegularCard;
import org.poo.model.transaction.AccountCreationTransaction;
import org.poo.model.transaction.MinBalanceSettingTransaction;
import org.poo.model.transaction.Transaction;
import org.poo.model.user.User;
import org.poo.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private Map<String, Account> accountsByIban = new HashMap<>();
    private UserService userService;

    public AccountService(UserService userService) {
        this.userService = userService;
    }

    public void clear() {
        accountsByIban.clear();
    }

    public Account createAccount(String email, String accountType, String currency, Double interestRate) {
        User user = userService.getUserByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        String iban = Utils.generateIBAN();

        Account account = new ClassicAccount(iban, user, currency);;
        /*if (accountType.equals("savings")) {
            account = new SavingsAccount(iban, user, currency, interestRate);
        } else {
            account = new ClassicAccount(iban, user, currency);
        }

         */

        //accountsByIban.put(account.getIban(), account);
        user.addAccount(account);
        accountsByIban.put(iban, account);
        /*Transaction txn = new AccountCreationTransaction(
                (int)(System.currentTimeMillis() / 1000),
                "Account Created",
                0.0,
                "success",
                accountType,
                currency,
                0.0
        );

        account.addTransaction(txn);

         */
        return account;
    }

    public void addFunds(String iban, Double amount) {
        Account account = getAccountByIBAN(iban);
        if (account == null) {
            throw new AccountNotFoundException("Account not found with IBAN: from addFunds " + iban);
        }

        account.deposit(amount);
    }

    public void deleteAccount(String iban, String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }

        Account accountToRemove = null;
        for (Account account : user.getAccounts()) {
            if (account.getIban().equals(iban)) {
                accountToRemove = account;
                break;
            }
        }

        if (accountToRemove == null) {
            throw new AccountNotFoundException("Account not found with IBAN: from deleteAccount" + iban);
        }

        if (accountToRemove.getBalance() != 0.0) {
            throw new IllegalArgumentException("Balance must be zero to delete the account.");
        }

        user.getAccounts().remove(accountToRemove);
        accountsByIban.remove(iban);
    }

    public Account getAccountByIBAN(String iban) {
        return accountsByIban.get(iban);
    }

    public void setMinBalance(String accountIBAN, double minBalance) {
        Account account = getAccountByIBAN(accountIBAN);
        /*if (!account.getOwner().getEmail().equals(email)) {
            throw new IllegalArgumentException("User does not own the account");
        }

         */
        account.setMinimumBalance(minBalance);

        Transaction txn = new MinBalanceSettingTransaction(
                (int)(System.currentTimeMillis()/1000),
                "Min Balance Set",
                0.0,
                "success",
                minBalance
        );
        account.addTransaction(txn);
    }


}
