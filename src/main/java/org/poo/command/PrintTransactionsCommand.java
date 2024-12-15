package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class PrintTransactionsCommand implements Command {
    private String command;
    private int timestamp;
    private String email;

    public PrintTransactionsCommand(String command, int timestamp, String email) {
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
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
