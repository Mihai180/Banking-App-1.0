package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

import java.util.List;

public class SplitPaymentCommand implements Command{
    private List<String> accounts;
    private int timestamp;
    private String currency;
    private double amount;

    public SplitPaymentCommand(List<String> accounts, int timestamp, String currency, double amount) {
        this.accounts = accounts;
        this.timestamp = timestamp;
        this.currency = currency;
        this.amount = amount;
    }

    public List<String> getAccounts() {
        return accounts;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
