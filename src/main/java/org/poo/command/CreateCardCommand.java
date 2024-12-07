package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class CreateCardCommand implements Command {
    private int timestamp;
    private String commandName;
    private String accountIBAN;
    private String cardType;
    private String email;

    public CreateCardCommand(String commandName, int timestamp,
                             String accountIBAN, String email) {
        this.commandName = commandName;
        this.timestamp = timestamp;
        this.accountIBAN = accountIBAN;
        this.cardType = cardType;
        this.email = email;
    }

    public String getCommandName() {
        return commandName;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getAccountIBAN() {
        return accountIBAN;
    }

    public String getCardType() {
        return cardType;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
