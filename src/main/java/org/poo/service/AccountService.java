package org.poo.service;

import org.poo.exception.AccountNotFoundException;
import org.poo.model.account.Account;
import org.poo.model.account.ClassicAccount;
import org.poo.model.account.SavingsAccount;
import org.poo.model.transaction.Transaction;
import org.poo.model.user.User;
import org.poo.utils.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AccountService {
    private Map<String, Account> accountsByIban = new HashMap<>();
    private UserService userService;
    private ExchangeService exchangeService;

    public AccountService(final UserService userService, final ExchangeService exchangeService) {
        this.userService = userService;
        this.exchangeService = exchangeService;
    }

    /**
     *
     */
    public void clear() {
        accountsByIban.clear();
    }

    /**
     *
     * @param email
     * @param accountType
     * @param currency
     * @param interestRate
     * @return
     */
    public Account createAccount(final String email, final String accountType,
                                 final String currency, final Double interestRate) {
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

        user.addAccount(account);
        accountsByIban.put(iban, account);
        return account;
    }

    /**
     *
     * @param iban
     * @param amount
     */
    public void addFunds(final String iban, final Double amount) {
        Account account = getAccountByIBAN(iban);
        if (account == null) {
            throw new AccountNotFoundException("Account not found with IBAN: from addFunds "
                    + iban);
        }
        account.deposit(amount);
    }

    /**
     *
     * @param iban
     * @param email
     */
    public void deleteAccount(final String iban, final String email) {
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
            throw new AccountNotFoundException("Account not found with IBAN: from deleteAccount"
                    + iban);
        }

        if (accountToRemove.getBalance() != 0.0) {
            throw new IllegalArgumentException("Account couldn't be deleted -"
                    + " see org.poo.transactions for details");
        }

        user.getAccounts().remove(accountToRemove);
        accountsByIban.remove(iban);
    }

    /**
     *
     * @param iban
     * @return
     */
    public Account getAccountByIBAN(final String iban) {
        return accountsByIban.get(iban);
    }

    /**
     *
     * @param accountIBAN
     * @param minBalance
     */
    public void setMinBalance(final String accountIBAN, final double minBalance) {
        Account account = getAccountByIBAN(accountIBAN);
        account.setMinimumBalance(minBalance);
    }

    /**
     *
     * @param senderIban
     * @param amount
     * @param receiverAliasOrIBAN
     * @return
     */
    public String sendMoney(final String senderIban, final double amount,
                            final String receiverAliasOrIBAN) {
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

    /**
     *
     * @param aliasOrIBAN
     * @return
     */
    private String resolveAliasOrIBAN(final String aliasOrIBAN) {
        for (User user : userService.getAllUsers().values()) {
            if (user.getAliases().containsKey(aliasOrIBAN)) {
                return user.getAliases().get(aliasOrIBAN);
            }
        }
        return aliasOrIBAN;
    }

    /**
     *
     * @param iban
     * @param interestRate
     * @return
     */
    public String changeInterestRate(final String iban, final Double interestRate) {
        Account account = getAccountByIBAN(iban);
        if (account == null) {
            throw new IllegalArgumentException("Account not found with IBAN: " + iban);
        }
        if (account.getAccountType().equals("classic")) {
            return "This is not a savings account";
        }
        if (account.getAccountType().equals("savings")) {
            account.changeInterestRate(interestRate);
        }
        return "Success";
    }

    /**
     *
     * @param accounts
     * @param currency
     * @param amount
     * @return
     */
    public String splitPayment(final List<String> accounts, final String currency,
                               final double amount) {
        int nrOfAccounts = accounts.size();
        double splitAmount = amount / nrOfAccounts;
        String lastIban = null;
        for (String iban : accounts) {
            Account account = getAccountByIBAN(iban);
            if (account == null) {
                return "Account not found with IBAN: " + iban;
            }
            double convertedAmount = splitAmount;
            if (!account.getCurrency().equals(currency)) {
                convertedAmount = exchangeService.convertCurrency(currency,
                        account.getCurrency(), splitAmount);
            }
            if (account.getBalance() < convertedAmount) {
                lastIban = iban;
            }
        }
        if (lastIban != null) {
            return "Account " + lastIban + " has insufficient funds for a split payment.";
        }
        for (String iban : accounts) {
            Account account = getAccountByIBAN(iban);
            if (account != null) {
                double convertedAmount = splitAmount;
                if (!account.getCurrency().equals(currency)) {
                    convertedAmount = exchangeService.convertCurrency(currency,
                            account.getCurrency(),
                            splitAmount);
                }
                account.withdraw(convertedAmount);
            }
        }
        return "Success";
    }

    /**
     *
     * @param iban
     * @return
     */
    public List<Transaction> getTransactions(final String iban) {
        Account account = getAccountByIBAN(iban);
        if (account == null) {
            throw new IllegalArgumentException("Account not found with IBAN: " + iban);
        }
        return account.getTransactions();
    }

    /**
     *
     * @param iban
     * @return
     */
    public String addInterestRate(final String iban) {
        Account account = getAccountByIBAN(iban);
        if (account == null) {
            return "Account not found with IBAN: " + iban;
        }
        if (account.getAccountType().equals("classic")) {
            return "This is not a savings account";
        }
        ((SavingsAccount) account).addInterest();
        return "Success";
    }
}
