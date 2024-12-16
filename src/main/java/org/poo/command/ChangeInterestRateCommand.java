package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public final class ChangeInterestRateCommand implements Command {
    private final int timestamp;
    private final String account;
    private final double intrestRate;
    private final String command;

    public ChangeInterestRateCommand(final int timestamp, final String account,
                                     final double intrestRate, final String command) {
        this.timestamp = timestamp;
        this.account = account;
        this.intrestRate = intrestRate;
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
        return intrestRate;
    }

    @Override
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
