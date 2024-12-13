package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class ChangeInterestRateCommand implements Command {
    private int timestamp;
    private String account;
    private double IntrestRate;
    private String command;

    public ChangeInterestRateCommand(int timestamp, String account, double IntrestRate, String command) {
        this.timestamp = timestamp;
        this.account = account;
        this.IntrestRate = IntrestRate;
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getAccount() {
        return account;
    }

    public double getIntrestRate() {
        return IntrestRate;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
