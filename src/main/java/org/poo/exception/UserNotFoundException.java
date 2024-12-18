package org.poo.exception;

/**
 * Excepție unchecked care indică faptul că un utilizator nu a fost găsit.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final String message) {
        super(message);
    }
}
