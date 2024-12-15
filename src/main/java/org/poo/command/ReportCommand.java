package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class ReportCommand implements Command {
    private String command;
    private int timestamp;
    private int startTimestamp;
    private int endTimestamp;
    private String account;

    public ReportCommand(String command, int timestamp, int startTimestamp, int endTimestamp, String account) {
        this.command = command;
        this.timestamp = timestamp;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.account = account;
    }

    public String getCommand() {
        return command;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public int getStartTimestamp() {
        return startTimestamp;
    }

    public int getEndTimestamp() {
        return endTimestamp;
    }

    public String getAccount() {
        return account;
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
