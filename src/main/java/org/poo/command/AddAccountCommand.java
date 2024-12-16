package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public final class AddAccountCommand implements Command {
    private final String commandName;
    private final int timestamp;
    private final String email;
    private final String accountType;
    private final String currency;
    private final Double interestRate;
    public AddAccountCommand(final String commandName, final int timestamp,
                             final String email, final String accountType, final String currency,
                             final Double interestRate) {
        this.commandName = commandName;
        this.timestamp = timestamp;
        this.email = email;
        this.accountType = accountType;
        this.currency = currency;
        this.interestRate = interestRate;
    }

    public String getCommandName() {
        return commandName;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getEmail() {
        return email;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    @Override
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
