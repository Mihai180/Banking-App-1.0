package org.poo.service;

import org.poo.exception.UserAlreadyExistsException;
import org.poo.exception.UserNotFoundException;
import org.poo.fileio.UserInput;
import org.poo.model.user.User;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Clasa finală UserService gestionează operațiunile legate de utilizatori
 */
public final class UserService {
    private Map<String, User> usersByEmail = new LinkedHashMap<>();

    /**
     * Creează un nou utilizator în sistem folosind informațiile furnizate prin obiectul UserInput
     * Verifică dacă un utilizator cu adresa de email specificată deja există pentru a
     * preveni duplicarea
     * @param userInput este Obiectul UserInput care conține datele necesare pentru crearea
     *                  unui utilizator
     * @throws UserAlreadyExistsException dacă un utilizator cu email-ul specificat deja există
     * în sistem
     */
    public void createUser(final UserInput userInput) {
        String email = userInput.getEmail();
        if (usersByEmail.containsKey(email)) {
            throw new UserAlreadyExistsException("User already exists: " + email);
        }

        User user = new User(userInput.getFirstName(), userInput.getLastName(), email);
        usersByEmail.put(email, user);
    }

    /**
     * Golește toți utilizatorii din sistem
     */
    public void clear() {
        usersByEmail.clear();
    }

    /**
     * Recuperează un utilizator pe baza adresei de email
     * @param email este Adresa de email a utilizatorului care se dorește a fi recuperat
     * @return obiectul User asociat adresei de email specificate
     * @throws UserNotFoundException dacă nu există niciun utilizator cu email-ul specificat
     * în sistem
     */
    public User getUserByEmail(final String email) {
        User user = usersByEmail.get(email);
        if (user == null) {
            throw new UserNotFoundException("User not found: " + email);
        }

        return user;
    }

    /**
     * Returnează o copie a tuturor utilizatorilor din sistem
     * Această metodă asigură că maparea internă a utilizatorilor nu poate fi modificată
     * din exterior
     * @return O mapare Map<String, User>, care conține toți utilizatorii, indexați după email
     */
    public Map<String, User> getAllUsers() {
        return new LinkedHashMap<>(usersByEmail);
    }

    /**
     * Asociază un alias unui cont specificat pentru un utilizator dat
     * @param email este adresa de email a utilizatorului pentru care se setează aliasul
     * @param aliasName este numele aliasului care va fi asociat contului
     * @param accountIban este IBAN-ul contului pentru care se creează aliasul
     * @throws UserNotFoundException dacă utilizatorul cu email-ul specificat nu este găsit
     */
    public void setAlias(final String email, final String aliasName, final String accountIban) {
        User user = getUserByEmail(email);
        user.addAlias(aliasName, accountIban);
    }
}
