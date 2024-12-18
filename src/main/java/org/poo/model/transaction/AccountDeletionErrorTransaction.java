package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

/**
 * Clasa finală AccountDeletionErrorTransaction reprezintă o tranzacție specifică care indică
 * o eroare la ștergerea unui cont
 */
public final class AccountDeletionErrorTransaction extends Transaction {
    public AccountDeletionErrorTransaction(final int timestamp) {
        super(timestamp);
        this.description = "Account couldn't be deleted - there are funds remaining";
    }

    @Override
    public String getType() {
        return "AccountDeletionError";
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

    /**
     * Acceptă un vizitator
     * @param visitor este obiectul care implementează interfața TransactionVisitor
     */
    @Override
    public void accept(final TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
