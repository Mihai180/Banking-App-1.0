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
    private ExchangeService exchangeService;
    private boolean minBalanceSet;

    public AccountService(UserService userService, ExchangeService exchangeService) {
        this.userService = userService;
        this.exchangeService = exchangeService;
        this.minBalanceSet = false;
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

        Account account = null;
        if (accountType.equals("savings")) {
            account = new SavingsAccount(iban, user, currency, interestRate);
        } else {
            account = new ClassicAccount(iban, user, currency);
        }

        //accountsByIban.put(account.getIban(), account);
        user.addAccount(account);
        accountsByIban.put(iban, account);

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
            throw new IllegalArgumentException("Account couldn't be deleted - see org.poo.transactions for details");
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

        /*Transaction txn = new MinBalanceSettingTransaction(
                (int)(System.currentTimeMillis()/1000),
                "Min Balance Set",
                0.0,
                "success",
                minBalance
        );
        account.addTransaction(txn);

         */
        minBalanceSet = true;
    }

    public String sendMoney(String senderIban, double amount, String receiverAliasOrIBAN) {
        String receiverIban = resolveAliasOrIBAN(receiverAliasOrIBAN);
        if (receiverIban == null) {
            return "Receiver account not found or invalid alias.";
        }

        Account senderAccount = getAccountByIBAN(senderIban);
        Account receiverAccount = getAccountByIBAN(receiverIban);

        if (senderAccount == null) {
            return "Sender account not found.";
        }

        if (receiverAccount == null) {
            return "Receiver account not found.";
        }

        double convertedAmount = exchangeService.convertCurrency(
                senderAccount.getCurrency(),
                receiverAccount.getCurrency(),
                amount
        );

        if (senderAccount.getBalance() < amount) {
            return "Insufficient funds in sender's account";
        }

        senderAccount.withdraw(amount);

        receiverAccount.deposit(convertedAmount);

        return "Success";
    }

    private String resolveAliasOrIBAN(String aliasOrIBAN) {
        for (User user : userService.getAllUsers().values()) {
            if (user.getAliases().containsKey(aliasOrIBAN)) {
                return user.getAliases().get(aliasOrIBAN);
            }
        }
        return aliasOrIBAN;
    }

    public boolean isMinBalanceSet() {
       return minBalanceSet;
    }
}
