package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;
import java.util.List;

public final class SplitPaymentTransaction extends Transaction {
    private final String currency;
    private final String amount;
    private final double splitAmount;
    private final List<String> involvedAccounts;

    public SplitPaymentTransaction(final int timestamp, final String currency, final String amount,
                                   final List<String> involvedAccounts, final double splitAmount) {
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

    @Override
    public String getType() {
        return "SplitPayment";
    }

    @Override
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
