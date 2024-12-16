package org.poo.model.user;

import org.poo.model.account.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class User {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final ArrayList<Account> accounts;
    private final Map<String, String> aliases;

    public User(final String firstName, final String lastName, final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accounts = new ArrayList<>();
        this.aliases = new HashMap<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public Map<String, String> getAliases() {
        return aliases;
    }

    /**
     *
     * @param account
     */
    public void addAccount(final Account account) {
        if (account != null) {
            accounts.add(account);
        }
   }

    /**
     *
     * @param aliasName
     * @param accountIban
     */
   public void addAlias(final String aliasName, final String accountIban) {
        aliases.put(aliasName, accountIban);
   }

    /**
     *
     * @param aliasName
     * @return
     */
   public Account getAccountByAlias(final String aliasName) {
        String accountIban = aliases.get(aliasName);
        if (accountIban != null) {
            for (Account account : accounts) {
                if (account.getIban().equals(accountIban)) {
                    return account;
                }
            }
        }
        return null;
   }
}
