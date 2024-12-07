package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class AddFundsCommand implements Command {
    private String CommandName;
    private int timestamp;
    private String accountIBAN;
    private double amount;

    public AddFundsCommand(String commandName, int timestamp,
                           String accountIBAN, double amount) {
        this.CommandName = commandName;
        this.timestamp = timestamp;
        this.accountIBAN = accountIBAN;
        this.amount = amount;
    }

    public String getCommandName() {
        return CommandName;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getAccountIBAN() {
        return accountIBAN;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
