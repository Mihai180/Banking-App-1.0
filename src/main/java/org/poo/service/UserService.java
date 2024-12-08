package org.poo.service;

import org.poo.exception.UserNotFoundException;
import org.poo.fileio.UserInput;
import org.poo.model.user.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> usersByEmail = new LinkedHashMap<>();

    public void createUser(UserInput userInput) {
        String email = userInput.getEmail();
        if (usersByEmail.containsKey(email)) {
            throw new IllegalArgumentException("User already exists: " + email);
        }

        User user = new User(userInput.getFirstName(), userInput.getLastName(), email);
        usersByEmail.put(email, user);
    }

    public void clear() {
        usersByEmail.clear();
    }

    public User getUserByEmail(String email) {
        User user = usersByEmail.get(email);
        if (user == null) {
            throw new UserNotFoundException("User not found: " + email);
        }

        return user;
    }

    public Map<String, User> getAllUsers() {
        return new LinkedHashMap<>(usersByEmail);
    }
}
