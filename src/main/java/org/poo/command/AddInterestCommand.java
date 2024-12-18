package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

/**
 * Comanda utilizată pentru încasarea dobânzii.
 * Această clasă stochează informațiile necesare pentru încasarea dobânzii,
 * iar logica comenzii este realizată de CommandVisitor
 */
public final class AddInterestCommand implements Command {
    private final String command;
    private final String account;
    private final int timestamp;
    public AddInterestCommand(final String command, final String account, final int timestamp) {
        this.command = command;
        this.account = account;
        this.timestamp = timestamp;
    }

    public String getCommand() {
        return command;
    }

    public String getAccount() {
        return account;
    }

    public int getTimestamp() {
        return timestamp;
    }

    @Override
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
