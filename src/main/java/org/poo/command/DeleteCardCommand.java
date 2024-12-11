package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class DeleteCardCommand implements Command {
    private int timestamp;
    private String cardNumber;
    private String email;

    public DeleteCardCommand(int timestamp, String cardNumber, String email) {
        this.timestamp = timestamp;
        this.cardNumber = cardNumber;
        this.email = email;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
