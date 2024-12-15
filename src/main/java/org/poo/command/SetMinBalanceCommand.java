package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class SetMinBalanceCommand implements Command{
    private String accountIban;
    private double minBalance;
    public SetMinBalanceCommand(String accountIban, double minBalance) {
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
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
