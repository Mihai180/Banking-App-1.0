package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public final class DeleteCardCommand implements Command {
    private final int timestamp;
    private final String cardNumber;
    private final String email;

    public DeleteCardCommand(final int timestamp, final String cardNumber, final String email) {
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
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
