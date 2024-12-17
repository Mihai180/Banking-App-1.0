package org.poo.exception;

public class NoExchangeRateException extends RuntimeException {
    public NoExchangeRateException(final String message) {
        super(message);
    }
}
