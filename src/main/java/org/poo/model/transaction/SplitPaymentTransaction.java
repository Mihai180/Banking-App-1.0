package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;
import java.util.List;

/**
 * Clasa finală SplitPaymentTransaction reprezintă o tranzacție specifică pentru
 * efectuarea unei plăți împărțite între mai multe conturi
 */
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

    /**
     * Acceptă un vizitator
     * @param visitor este obiectul care implementează interfața TransactionVisitor
     */
    @Override
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
