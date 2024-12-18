package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

/**
 * Comanda utilizată pentru depunerea de fonduri în cont.
 * Această clasă stochează informațiile necesare pentru depunerea de fonduri în cont
 * iar logica comenzii este realizată de CommandVisitor.
 */
public final class AddFundsCommand implements Command {
    private final String commandName;
    private final int timestamp;
    private final String accountIBAN;
    private final double amount;

    public AddFundsCommand(final String commandName, final int timestamp,
                           final String accountIBAN, final double amount) {
        this.commandName = commandName;
        this.timestamp = timestamp;
        this.accountIBAN = accountIBAN;
        this.amount = amount;
    }

    public String getCommandName() {
        return commandName;
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
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
