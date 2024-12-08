package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class PayOnlineCommand implements Command {
    private String cardNumber;
    private double amount;
    private String currency;
    private int timestamp;
    private String description;
    private String commerciant;
    private String email;

    public PayOnlineCommand(String cardNumber, double amount, String currency, int timestamp, String description, String commercient, String email) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.description = description;
        this.commerciant = commercient;
        this.email = email;
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

    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
