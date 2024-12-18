package org.poo.exception;

/**
 * Excepție unchecked care indică faptul că un card nu a fost găsit.
 */
public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(final String message) {
        super(message);
    }
}
