package org.poo.exception;

public class CardIsUsedException extends RuntimeException {
    public CardIsUsedException(final String message) {
        super(message);
    }

}
