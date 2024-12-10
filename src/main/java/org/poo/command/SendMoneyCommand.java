package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class SendMoneyCommand implements Command {
    private String account;
    private double amount;
    private String reciever;
    private int timestamp;
    private String description;

    public SendMoneyCommand(String account, double amount, String reciever, int timestamp, String description) {
        this.account = account;
        this.amount = amount;
        this.reciever = reciever;
        this.timestamp = timestamp;
        this.description = description;
    }

    public String getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public String getReciever() {
        return reciever;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
