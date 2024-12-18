package org.poo.exception;

/**
 * Excepție unchecked care indică faptul că un cont nu a fost găsit.
 */
public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(final String message) {
        super(message);
    }
}
