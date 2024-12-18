package org.poo.model.transaction;

import org.poo.visitor.transaction.TransactionVisitor;

/**
 * Clasa finală FrozenCardTransaction reprezintă o tranzacție specifică care indică faptul
 * că un card a fost blocat (frozen)
 */
public final class FrozenCardTransaction extends Transaction {
    public FrozenCardTransaction(final int timestamp) {
        super(timestamp);
        this.description = "The card is frozen";
    }

    @Override
    public String getType() {
        return "FrozenCard";
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
