package org.poo.visitor.transaction;

import org.poo.model.transaction.AccountCreationTransaction;
import org.poo.model.transaction.AccountDeletionErrorTransaction;
import org.poo.model.transaction.CardCreationTransaction;
import org.poo.model.transaction.CardDeletionTransaction;
import org.poo.model.transaction.CardPaymentTransaction;
import org.poo.model.transaction.FrozenCardTransaction;
import org.poo.model.transaction.InssuficientFundsForSplitTransaction;
import org.poo.model.transaction.InsufficientFundsTransaction;
import org.poo.model.transaction.InterestRateChangeTransaction;
import org.poo.model.transaction.MinimumAmountOfFundsTransaction;
import org.poo.model.transaction.SendMoneyTransaction;
import org.poo.model.transaction.SplitPaymentTransaction;

public interface TransactionVisitor {
    /**
     *
     * @param transaction
     */
    void visit(AccountCreationTransaction transaction);

    /**
     *
     * @param transaction
     */
    void visit(CardCreationTransaction transaction);

    /**
     *
     * @param transaction
     */
    void visit(SendMoneyTransaction transaction);

    /**
     *
     * @param transaction
     */
    void visit(CardPaymentTransaction transaction);

    /**
     *
     * @param transaction
     */
    void visit(InsufficientFundsTransaction transaction);

    /**
     *
     * @param transaction
     */
    void visit(CardDeletionTransaction transaction);

    /**
     *
     * @param transaction
     */
    void visit(MinimumAmountOfFundsTransaction transaction);

    /**
     *
     * @param transaction
     */
    void visit(FrozenCardTransaction transaction);

    /**
     *
     * @param transaction
     */
    void visit(SplitPaymentTransaction transaction);

    /**
     *
     * @param transaction
     */
    void visit(InssuficientFundsForSplitTransaction transaction);

    /**
     *
     * @param transaction
     */
    void visit(AccountDeletionErrorTransaction transaction);

    /**
     *
     * @param transaction
     */
    void visit(InterestRateChangeTransaction transaction);
}
