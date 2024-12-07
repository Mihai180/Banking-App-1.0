package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class ErrorTransaction extends Transaction {
    private String errorMessage;

    public ErrorTransaction(int timestamp, String description, double amount, String status,
                            String errorMessage) {
        super(timestamp, description, amount, status);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() { return errorMessage; }

    @Override
    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }

}
