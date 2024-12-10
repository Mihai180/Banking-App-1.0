package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

public class ErrorTransaction extends Transaction {
    private String errorMessage;

    public ErrorTransaction(int timestamp,
                            String errorMessage) {
        super(timestamp);
        this.description = "Error";
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() { return errorMessage; }

    @Override
    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }

}
