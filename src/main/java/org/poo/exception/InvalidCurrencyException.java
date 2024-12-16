package org.poo.exception;

public class InvalidCurrencyException extends RuntimeException {
    public InvalidCurrencyException(final String message) {
        super(message);
    }
}
