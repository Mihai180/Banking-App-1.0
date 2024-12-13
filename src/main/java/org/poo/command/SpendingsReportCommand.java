package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class SpendingsReportCommand implements Command {
    private int startTimestamp;
    private int endTimestamp;
    private int timestamp;
    private String command;
    private String account;

    public SpendingsReportCommand(int startTimestamp, int endTimestamp, int timestamp, String command, String account) {
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
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
