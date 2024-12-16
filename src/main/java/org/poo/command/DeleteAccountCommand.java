package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public final class DeleteAccountCommand implements Command {
    private final String commandName;
    private final int timestamp;
    private final String account;
    private final String email;

    public DeleteAccountCommand(final String commandName, final int timestamp,
                                final String account, final String email) {
        this.commandName = commandName;
        this.timestamp = timestamp;
        this.account = account;
        this.email = email;
    }

    public String getCommandName() {
        return commandName;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getAccount() {
        return account;
    }

    public String getEmail() {
        return email;
    }

    @Override
   public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
