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
    // Instanța unică a clasei TransactionService
    private static TransactionService instance;
    private UserService userService;

    // Constructor privat pentru a preveni instanțierea directă din exterior
    private TransactionService(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Metodă statică pentru a obține instanța unică a clasei TransactionService
     * Dacă instanța nu există, aceasta este creată
     * @param userService este instanța UserService necesară pentru TransactionService
     * @return instanța unică a TransactionService
     */
    public static TransactionService getInstance(final UserService userService) {
        if (instance == null) {
            instance = new TransactionService(userService);
        }
        return instance;
    }

    /**
     * Metodă statică pentru a reseta instanța unică a clasei TransactionService
     * Folosită pentru resetarea instanței între teste
     */
    public static void resetInstance() {
        instance = null;
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
