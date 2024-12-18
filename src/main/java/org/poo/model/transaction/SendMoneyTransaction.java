package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

/**
 * Clasa finală SendMoneyTransaction reprezintă o tranzacție specifică pentru transferul de bani
 * între două conturi
 */
public final class SendMoneyTransaction extends Transaction {
    private final String sender;
    private final String receiver;
    private final double amount;
    private final String currency;
    private final String transferType;

    public SendMoneyTransaction(final int timestamp, final String description, final String sender,
                                final String receiver, final double amount, final String currency,
                                final String transferType) {
        super(timestamp);
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.description = description;
        this.currency = currency;
        this.transferType = transferType;
    }

    public String getTransferType() {
        return transferType;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
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
        return "SendMoney";
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
