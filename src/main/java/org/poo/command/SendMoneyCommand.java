package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public final class SendMoneyCommand implements Command {
    private final String account;
    private final double amount;
    private final String reciever;
    private final int timestamp;
    private final String description;

    public SendMoneyCommand(final String account, final double amount, final String reciever,
                            final int timestamp, final String description) {
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

    @Override
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
