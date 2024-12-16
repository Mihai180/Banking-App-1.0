package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public final class NotImplementedCommand implements Command {
    private final String commandName;
    private final int timestamp;

    public NotImplementedCommand(final String commandName, final int timestamp) {
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
