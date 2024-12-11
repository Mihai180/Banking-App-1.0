package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

import java.io.Serializable;

public class MinimumAmountOfFundsTransaction extends Transaction{
    public MinimumAmountOfFundsTransaction (int timestamp) {
        super(timestamp);
        this. description = "You have reached the minimum amount of funds, the card will be frozen";
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
