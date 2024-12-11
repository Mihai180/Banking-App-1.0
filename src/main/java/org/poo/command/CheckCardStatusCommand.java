package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class CheckCardStatusCommand implements Command{
    private String command;
    private String cardNumber;
    private int timestamp;

    public CheckCardStatusCommand(String cardNumber, int timestamp, String command) {
        this.command = command;
        this.cardNumber = cardNumber;
        this.timestamp = timestamp;
    }

    public String getCommand() {
        return command;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
