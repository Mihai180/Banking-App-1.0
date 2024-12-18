package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

/**
 * Comanda utilizată pentru afișarea utilizatorilor.
 * Această clasă stochează informațiile necesare pentru afișarea utilizatorilor,
 * iar logica comenzii este realizată de CommandVisitor
 */
public final class PrintUsersCommand implements Command {
    private final String commandName;
    private final int timestamp;
    public PrintUsersCommand(final String commandName, final int timestamp) {
        this.commandName = commandName;
        this.timestamp = timestamp;
    }

    public String getCommandName() {
        return commandName;
    }

    public int getTimestamp() {
        return timestamp;
    }

    @Override
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
