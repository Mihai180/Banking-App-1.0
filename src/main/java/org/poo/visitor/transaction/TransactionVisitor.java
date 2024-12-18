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

/**
 * Interfața TransactionVisitor definește metodele de vizitare pentru diferite tipuri de tranzacții.
 * Aceasta permite implementarea modelului Visitor, facilitând adăugarea de noi operațiuni asupra
 * tranzacțiilor fără a modifica clasele de tranzacții existente.
 */
public interface TransactionVisitor {
    /**
     * Vizitează o tranzacție de creare a contului.
     *
     * @param transaction este tranzacția de creare a contului.
     */
    void visit(AccountCreationTransaction transaction);

    /**
     * Vizitează o tranzacție de creare a unui card.
     *
     * @param transaction este tranzacția de creare a cardului.
     */
    void visit(CardCreationTransaction transaction);

    /**
     * Vizitează o tranzacție de trimitere de bani.
     *
     * @param transaction este tranzacția de trimitere de bani.
     */
    void visit(SendMoneyTransaction transaction);

    /**
     * Vizitează o tranzacție de plată cu cardul.
     *
     * @param transaction este tranzacția de plată cu cardul.
     */
    void visit(CardPaymentTransaction transaction);

    /**
     * Vizitează o tranzacție de fonduri insuficiente.
     *
     * @param transaction este tranzacția de fonduri insuficiente.
     */
    void visit(InsufficientFundsTransaction transaction);

    /**
     * Vizitează o tranzacție de ștergere a unui card.
     *
     * @param transaction este tranzacția de ștergere a cardului.
     */
    void visit(CardDeletionTransaction transaction);

    /**
     * Vizitează o tranzacție de fonduri insuficiente pentru soldul minim.
     *
     * @param transaction este tranzacția de fonduri insuficiente pentru soldul minim.
     */
    void visit(MinimumAmountOfFundsTransaction transaction);

    /**
     * Vizitează o tranzacție de blocare a cardului.
     *
     * @param transaction este tranzacția de blocare a cardului.
     */
    void visit(FrozenCardTransaction transaction);

    /**
     * Vizitează o tranzacție de plată împărțită.
     *
     * @param transaction este tranzacția de plată împărțită.
     */
    void visit(SplitPaymentTransaction transaction);

    /**
     * Vizitează o tranzacție de fonduri insuficiente pentru o plată împărțită.
     *
     * @param transaction este tranzacția de fonduri insuficiente pentru plata împărțită.
     */
    void visit(InssuficientFundsForSplitTransaction transaction);

    /**
     * Vizitează o tranzacție de eroare la ștergerea contului.
     *
     * @param transaction este tranzacția de eroare la ștergerea contului.
     */
    void visit(AccountDeletionErrorTransaction transaction);

    /**
     * Vizitează o tranzacție de schimbare a ratei dobânzii.
     *
     * @param transaction este tranzacția de schimbare a ratei dobânzii.
     */
    void visit(InterestRateChangeTransaction transaction);
}
