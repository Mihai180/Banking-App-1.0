package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

import java.util.List;

public final class InssuficientFundsForSplitTransaction extends Transaction {
    private final double amount;
    private final double splitAmount;
    private final String currency;
    private final String error;
    private final List<String> involvedAccounts;

    public InssuficientFundsForSplitTransaction(final double amount, final String currency,
                                                final List<String> involvedAccounts,
                                                final int timestamp, final String error,
                                                final double splitAmount) {
        super(timestamp);
        this.splitAmount = splitAmount;
        this.description = "Split payment of " + String.format("%.2f", amount) + " " + currency;
        this.amount = amount;
        this.error = error;
        this.currency = currency;
        this.involvedAccounts = involvedAccounts;
    }

    public double getSplitAmount() {
        return splitAmount;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getError() {
        return error;
    }

    public List<String> getInvolvedAccounts() {
        return involvedAccounts;
    }

    public int getTimestamp() {
        return timestamp;
    }

    @Override
    public String getType() {
        return "InssuficientFundsForSplit";
    }

    @Override
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
