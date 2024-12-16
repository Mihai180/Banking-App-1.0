package org.poo.service;

import org.poo.exception.UserNotFoundException;
import org.poo.model.account.Account;
import org.poo.model.transaction.Transaction;
import org.poo.model.user.User;
import java.util.ArrayList;
import java.util.List;

public final class TransactionService {
    private UserService userService;

    public TransactionService(final UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param email
     * @return
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
