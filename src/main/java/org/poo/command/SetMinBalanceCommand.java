package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public final class SetMinBalanceCommand implements Command {
    private final String accountIban;
    private final double minBalance;
    public SetMinBalanceCommand(final String accountIban, final double minBalance) {
        this.accountIban = accountIban;
        this.minBalance = minBalance;
    }

    public String getAccountIban() {
        return accountIban;
    }

    public double getMinBalance() {
        return minBalance;
    }

    @Override
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
