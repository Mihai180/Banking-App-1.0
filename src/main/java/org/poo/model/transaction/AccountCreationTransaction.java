package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class AccountCreationTransaction extends Transaction {
    private String accountType;
    private String currency;
    private double initialDeposit;
    public AccountCreationTransaction(int timestamp, String description, double amount, String status,
            String accountType, String currency, double initialDeposit) {
        super(timestamp, description, amount, status);
        this.accountType = accountType;
        this.currency = currency;
        this.initialDeposit = initialDeposit;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public double getInitialDeposit() {
        return initialDeposit;
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
