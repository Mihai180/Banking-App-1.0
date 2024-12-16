package org.poo.service;

import org.poo.exception.UserNotFoundException;
import org.poo.fileio.UserInput;
import org.poo.model.user.User;
import java.util.LinkedHashMap;
import java.util.Map;

public final class UserService {
    private Map<String, User> usersByEmail = new LinkedHashMap<>();

    /**
     *
     * @param userInput
     */
    public void createUser(final UserInput userInput) {
        String email = userInput.getEmail();
        if (usersByEmail.containsKey(email)) {
            throw new IllegalArgumentException("User already exists: " + email);
        }

        User user = new User(userInput.getFirstName(), userInput.getLastName(), email);
        usersByEmail.put(email, user);
    }

    /**
     *
     */
    public void clear() {
        usersByEmail.clear();
    }

    /**
     *
     * @param email
     * @return
     */
    public User getUserByEmail(final String email) {
        User user = usersByEmail.get(email);
        if (user == null) {
            throw new UserNotFoundException("User not found: " + email);
        }

        return user;
    }

    /**
     *
     * @return
     */
    public Map<String, User> getAllUsers() {
        return new LinkedHashMap<>(usersByEmail);
    }

    /**
     *
     * @param email
     * @param aliasName
     * @param accountIban
     */
    public void setAlias(final String email, final String aliasName, final String accountIban) {
        User user = getUserByEmail(email);
        user.addAlias(aliasName, accountIban);
    }
}
