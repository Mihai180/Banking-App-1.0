package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

/**
 * Interfață care reprezintă o comandă ce poate fi procesată de un CommandVisitor.
 */
public interface Command {
    /**
     * Permite unui obiect de tip CommandVisitor să viziteze comanda curentă,
     * declanșând logica aferentă procesării acesteia.
     * @param visitor este vizitatorul ce va procesa comanda
     */
    void accept(CommandVisitor visitor);
}
