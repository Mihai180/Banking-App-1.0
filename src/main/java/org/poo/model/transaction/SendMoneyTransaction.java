package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class SendMoneyTransaction extends Transaction {
    private String sender;
    private String receiver;
    private double amount;
    private String currency;
    private String transferType;

    public SendMoneyTransaction(int timestamp, String description, String sender, String receiver,
                                double amount, String currency, String transferType) {
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

    public String getType() {
        return "SendMoney";
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
