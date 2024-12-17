package org.poo.exception;

public class AccountCanNotBeDeletedException extends RuntimeException {
    public AccountCanNotBeDeletedException(final String message) {
        super(message);
    }
}
