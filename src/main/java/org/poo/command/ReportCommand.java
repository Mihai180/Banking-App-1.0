package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

/**
 * Comanda utilizată pentru generarea unui raport cu toate tranzacțiile.
 * Această clasă stochează informațiile necesare pentru generarea raportului,
 * iar logica comenzii este realizată de CommandVisitor
 */
public final class ReportCommand implements Command {
    private final String command;
    private final int timestamp;
    private final int startTimestamp;
    private final int endTimestamp;
    private final String account;

    public ReportCommand(final String command, final int timestamp, final int startTimestamp,
                         final int endTimestamp, final String account) {
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
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
