package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class DeleteCardCommand implements Command {
    private String cardNumber;
    private String email;

    public DeleteCardCommand(String cardNumber, String email) {
        this.cardNumber = cardNumber;
        this.email = email;
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
