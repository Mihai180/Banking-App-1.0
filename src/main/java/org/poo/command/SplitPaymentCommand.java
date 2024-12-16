package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

import java.util.List;

public final class SplitPaymentCommand implements Command {
    private final List<String> accounts;
    private final int timestamp;
    private final String currency;
    private final double amount;

    public SplitPaymentCommand(final List<String> accounts, final int timestamp,
                               final String currency, final double amount) {
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
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
