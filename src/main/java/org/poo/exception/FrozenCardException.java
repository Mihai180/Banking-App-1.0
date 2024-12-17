package org.poo.exception;

public class FrozenCardException extends RuntimeException {
    public FrozenCardException(final String message) {
        super(message);
    }
}
