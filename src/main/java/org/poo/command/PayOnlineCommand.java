package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class PayOnlineCommand implements Command {
    private String name;
    private String cardNumber;
    private double amount;
    private String currency;
    private int timestamp;
    private String description;
    private String commerciant;
    private String email;

    public PayOnlineCommand(String name, String cardNumber, double amount, String currency, int timestamp, String description, String commerciant, String email) {
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

    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
