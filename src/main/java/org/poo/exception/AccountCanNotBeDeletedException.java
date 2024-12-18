package org.poo.exception;

/**
 * Excepție unchecked care indică imposibilitatea ștergerii unui cont.
 */
public class AccountCanNotBeDeletedException extends RuntimeException {
    public AccountCanNotBeDeletedException(final String message) {
        super(message);
    }
}
