package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;
import java.util.List;

/**
 * Clasa finală InssuficientFundsForSplitTransaction reprezintă o tranzacție specifică care indică
 * o eroare de fonduri insuficiente pentru efectuarea unei plăți împărțite între mai multe conturi
 */
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

    /**
     * Folosită doar la card payment transaction
     * @return null
     */
    @Override
    public String getPaymentCommerciant() {
        return null;
    }

    /**
     * Folosită doar la card payment transaction
     * @return 0
     */
    @Override
    public double getPaymentAmount() {
        return 0;
    }

    @Override
    public String getType() {
        return "InssuficientFundsForSplit";
    }

    /**
     * Acceptă un vizitator
     * @param visitor este obiectul care implementează interfața TransactionVisitor
     */
    @Override
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
