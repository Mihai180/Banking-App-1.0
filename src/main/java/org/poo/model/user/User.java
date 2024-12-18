package org.poo.model.user;

import org.poo.model.account.Account;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clasa finală User reprezintă un utilizator al sistemului bancar, conținând informații personale
 * și gestionând conturile asociate acestuia
 */
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
     * Adaugă un cont nou la lista de conturi ale utilizatorului
     * Dacă contul nu este null, acesta este adăugat în listă
     * @param account este contul care trebuie adăugat
     */
    public void addAccount(final Account account) {
        if (account != null) {
            accounts.add(account);
        }
   }

    /**
     * Adaugă un alias pentru un cont specificat prin IBAN
     * @param aliasName este numele aliasului care va fi asociat contului
     * @param accountIban este IBAN-ul contului pentru care se creează aliasul
     */
   public void addAlias(final String aliasName, final String accountIban) {
        aliases.put(aliasName, accountIban);
   }
}
