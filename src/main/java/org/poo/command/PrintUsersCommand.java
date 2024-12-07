package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class PrintUsersCommand implements Command {
    private String commandName;
    private int timestamp;
    public PrintUsersCommand(String commandName, int timestamp) {
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
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
