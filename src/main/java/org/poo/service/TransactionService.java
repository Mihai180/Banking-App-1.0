package org.poo.service;

import org.poo.exception.UserNotFoundException;
import org.poo.model.account.Account;
import org.poo.model.transaction.Transaction;
import org.poo.model.user.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa finală TransactionService gestionează operațiunile legate de tranzacții pentru utilizatori
 */
public final class TransactionService {
    private UserService userService;

    public TransactionService(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Colectează lista completă de tranzacții pentru un utilizator specificat prin email
     * Această metodă colectează toate tranzacțiile din toate conturile asociate utilizatorului
     * @param email este adresa de email a utilizatorului pentru care se solicită tranzacțiile
     * @return O listă de obiecte Transaction reprezentând toate tranzacțiile utilizatorului
     * @throws UserNotFoundException dacă utilizatorul cu email-ul specificat nu este găsit
     */
    public List<Transaction> getTransactionsForUser(final String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found: " + email);
        }

        List<Transaction> allTransactions = new ArrayList<>();
        for (Account account : user.getAccounts()) {
            for (Transaction transaction : account.getTransactions()) {
                allTransactions.add(transaction);
            }
        }

        return allTransactions;
    }
}
