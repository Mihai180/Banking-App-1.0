package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

/**
 * Comanda utilizată pentru crearea unui nou card pentru un cont.
 * Această clasă stochează informațiile necesare pentru ccrearea unui nou card,
 * iar logica comenzii este realizată de CommandVisitor
 */
public final class CreateCardCommand implements Command {
    private final int timestamp;
    private final String commandName;
    private final String accountIBAN;
    private String cardType;
    private final String email;

    public CreateCardCommand(final String commandName, final int timestamp,
                             final String accountIBAN, final String email) {
        this.commandName = commandName;
        this.timestamp = timestamp;
        this.accountIBAN = accountIBAN;
        if (commandName.equals("createCard")) {
            this.cardType = "regularCard";
        } else if (commandName.equals("createOneTimeCard")) {
            this.cardType = "oneTimeCard";
        }
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
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
