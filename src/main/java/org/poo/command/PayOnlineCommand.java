package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public final class PayOnlineCommand implements Command {
    private final String name;
    private final String cardNumber;
    private final double amount;
    private final String currency;
    private final int timestamp;
    private final String description;
    private final String commerciant;
    private final String email;

    public PayOnlineCommand(final String name, final String cardNumber, final double amount,
                            final String currency, final int timestamp, final String description,
                            final String commerciant, final String email) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.description = description;
        this.commerciant = commerciant;
        this.email = email;
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public String getCommerciant() {
        return commerciant;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
