package org.poo.model.transaction;

import org.poo.model.merchant.Merchant;
import org.poo.visitor.transaction.TransactionVisitor;

import java.util.List;
import java.util.Map;

public class SplitPaymentTransaction extends Transaction {
    String currency;
    String amount;
    double splitAmount;
    List<String> involvedAccounts;

    public SplitPaymentTransaction(int timestamp, String currency, String amount, List<String> involvedAccounts, double splitAmount) {
        super(timestamp);
        this.description = "Split payment of ";
        this.currency = currency;
        this.amount = amount;
        this.involvedAccounts = involvedAccounts;
        this.splitAmount = splitAmount;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public List<String> getInvolvedAccounts() {
        return involvedAccounts;
    }

    public double getSplitAmount() {
        return splitAmount;
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
