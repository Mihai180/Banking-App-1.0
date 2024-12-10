package org.poo.model.user;

import org.poo.model.account.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<Account> accounts;
    private Map<String, String> aliases;

    public User (String firstName, String lastName, String email) {
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

   public void addAccount(Account account) {
        if (account != null) {
            accounts.add(account);
        }
   }

   public void addAlias(String aliasName, String AccountIban) {
        aliases.put(aliasName, AccountIban);
   }

   public Account getAccountByAlias(String aliasName) {
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
