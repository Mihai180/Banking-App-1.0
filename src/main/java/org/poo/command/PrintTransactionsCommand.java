package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public final class PrintTransactionsCommand implements Command {
    private final String command;
    private final int timestamp;
    private final String email;

    public PrintTransactionsCommand(final String command, final int timestamp, final String email) {
        this.command = command;
        this.timestamp = timestamp;
        this.email = email;
    }

    public String getCommand() {
        return command;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
