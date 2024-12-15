package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class AddInterestCommand implements Command {
    private String command;
    private String account;
    private int timestamp;
    public AddInterestCommand(String command, String account, int timestamp) {
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
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
