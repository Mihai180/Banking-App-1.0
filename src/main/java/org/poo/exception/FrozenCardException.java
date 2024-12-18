package org.poo.exception;

/**
 * Excepție unchecked care indică faptul că un card este frozen.
 */
public class FrozenCardException extends RuntimeException {
    public FrozenCardException(final String message) {
        super(message);
    }
}
