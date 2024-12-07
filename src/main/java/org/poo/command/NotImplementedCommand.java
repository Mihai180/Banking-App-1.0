package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class NotImplementedCommand implements Command {
    private String commandName;
    private int timestamp;

    public NotImplementedCommand(String commandName, int timestamp) {
        this.commandName = commandName;
        this.timestamp = timestamp;
    }

    public String getCommandName() { return commandName; }
    public int getTimestamp() { return timestamp; }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this); // The visitor can then print a "not implemented" message
    }
}
