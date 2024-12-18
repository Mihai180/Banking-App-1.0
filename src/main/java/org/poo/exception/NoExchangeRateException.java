package org.poo.exception;

/**
 * Excepție unchecked care indică faptul că nu există exchange rate între două monede.
 */
public class NoExchangeRateException extends RuntimeException {
    public NoExchangeRateException(final String message) {
        super(message);
    }
}
