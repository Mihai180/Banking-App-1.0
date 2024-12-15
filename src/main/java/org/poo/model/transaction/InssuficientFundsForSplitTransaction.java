package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

import java.util.ArrayList;
import java.util.List;

public class InssuficientFundsForSplitTransaction extends Transaction {
    private double amount;
    private double splitAmount;
    private String currency;
    private String error;
    private List<String> involvedAccounts;

    public InssuficientFundsForSplitTransaction(double amount, String currency, List<String> involvedAccounts, int timestamp, String error, double splitAmount) {
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

    public String getType() {
        return "InssuficientFundsForSplit";
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
