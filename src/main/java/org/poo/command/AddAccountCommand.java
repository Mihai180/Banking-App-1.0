package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class AddAccountCommand implements Command {
    private String commandName;
    private int timestamp;
    private String email;
    private String accountType;
    private String currency;
    private Double interestRate;
    public AddAccountCommand(String commandName, int timestamp,
                             String email, String accountType, String currency, Double interestRate) {
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
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
