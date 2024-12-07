package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class MinBalanceSettingTransaction extends Transaction {
    private double minBalance;

    public MinBalanceSettingTransaction(int timestamp, String description, double amount,
                                        String status, double minBalance) {
        super(timestamp, description, amount, status);
        this.minBalance = minBalance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
