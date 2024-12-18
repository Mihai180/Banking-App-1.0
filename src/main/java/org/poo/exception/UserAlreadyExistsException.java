package org.poo.exception;

/**
 * Excepție unchecked care indică faptul că un utilizator deja există.
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(final String message) {
        super(message);
    }
}
