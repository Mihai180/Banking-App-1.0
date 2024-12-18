package org.poo.exception;

/**
 * Excepție unchecked care indică faptul că nu există suficiente fonduri pentru
 * efectuarea unei plăți.
 */
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(final String message) {
        super(message);
    }
}
