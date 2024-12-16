package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public final class SpendingsReportCommand implements Command {
    private final int startTimestamp;
    private final int endTimestamp;
    private final int timestamp;
    private final String command;
    private final String account;

    public SpendingsReportCommand(final int startTimestamp, final int endTimestamp,
                                  final int timestamp, final String command,
                                  final String account) {
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.timestamp = timestamp;
        this.command = command;
        this.account = account;
    }

    public int getStartTimestamp() {
        return startTimestamp;
    }

    public int getEndTimestamp() {
        return endTimestamp;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getCommand() {
        return command;
    }

    public String getAccount() {
        return account;
    }

    @Override
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
