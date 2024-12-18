package org.poo.exception;

/**
 * Excepție unchecked care indică faptul că un card one time pay a fost folosit deja.
 */
public class CardIsUsedException extends RuntimeException {
    public CardIsUsedException(final String message) {
        super(message);
    }

}
