package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

/**
 * Clasa abstractă Transaction reprezintă o tranzacție generală
 */
public abstract class Transaction {
    protected int timestamp;
    protected String description;

    public Transaction(final int timestamp) {
        this.description = "";
        this.timestamp = timestamp;
    }

    /**
     * @return descrierea tranzacției
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return timestamp-ul tranzacției
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * @return tipul specific al tranzacției
     */
    public abstract String getType();

    /**
     * Acceptă un vizitator
     * @param visitor este obiectul care implementează interfața TransactionVisitor
     */
    public abstract void accept(TransactionVisitor visitor);

    /**
     * Este folosită doar pentru card payment transaction
     * @return numele comerciantului implicat în tranzacție, dacă este cazul
     */
    public abstract String getPaymentCommerciant();

    /**
     * Este folosită doar pentru card payment transaction
     * @return returnează suma plății asociate tranzacției, dacă este cazul
     */
    public abstract double getPaymentAmount();
}
