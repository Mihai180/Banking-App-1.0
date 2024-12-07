package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class DeleteAccountCommand implements Command {
    private String commandName;
    private int timestamp;
    private String account;
    private String email;

    public DeleteAccountCommand(String commandName, int timestamp, String account, String email) {
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

   public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }


}
