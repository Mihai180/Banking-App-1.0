package org.poo.exception;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(final String message) {
        super(message);
    }
}
