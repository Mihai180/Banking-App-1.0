package org.poo.exception;

/**
 * Excepție unchecked care indică faptul că utilizatorul nu are acces la
 * ce încearcă să acceseze.
 */
public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(final String message) {
        super(message);
    }
}
