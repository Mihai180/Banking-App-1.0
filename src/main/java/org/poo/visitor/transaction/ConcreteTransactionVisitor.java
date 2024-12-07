package org.poo.visitor.transaction;

import org.poo.model.transaction.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConcreteTransactionVisitor implements TransactionVisitor {
    // Map pentru cantitatea de tranzactii per tipul de tranzactie
    private Map<String, Double> totalAmountsByType;

    // Map pentru tranzactii in functie de categorie (pentru PaymentTransactions)
    private Map<String, Double> spendingByCategory;

    private ArrayList<Transaction> allTransactions;

    public ConcreteTransactionVisitor() {
        this.totalAmountsByType = new HashMap<>();
        this.spendingByCategory = new HashMap<>();
        this.allTransactions = new ArrayList<>();
    }

    private void processTransaction(Transaction transaction) {
        allTransactions.add(transaction);

        String transactionType = transaction.getClass().getSimpleName();

        double amount = transaction.getAmount();

        totalAmountsByType.merge(transactionType, amount, Double::sum);
    }

    public Map<String, Double> getTotalAmountsByType() {
        return totalAmountsByType;
    }

    public Map<String, Double> getSpendingByCategory() {
        return spendingByCategory;
    }

    public ArrayList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    @Override
    public void visit(AccountCreationTransaction transaction) {
        processTransaction(transaction);
    }

    @Override
    public void visit(BankTransferTransaction transaction) {
        processTransaction(transaction);
    }

    @Override
    public void visit(CardCreationTransaction transaction) {
        processTransaction(transaction);
    }

    @Override
    public void visit(CardDeletionTransaction transaction) {
        processTransaction(transaction);
    }

    @Override
    public void visit(InterestRateChangeTransaction transaction) {
        processTransaction(transaction);
    }

    @Override
    public void visit(InterestTransaction transaction) {
        processTransaction(transaction);
    }

    @Override
    public void visit(MinBalanceSettingTransaction transaction) {
        processTransaction(transaction);
    }

    @Override
    public void visit(PaymentTransaction transaction) {
        processTransaction(transaction);
        String category = transaction.getCategory();
        double amount = transaction.getAmount();
        spendingByCategory.merge(category, amount, Double::sum);
    }

    @Override
    public void visit(SplitPaymentTransaction transaction) {
        processTransaction(transaction);
    }

    @Override
    public void visit(ErrorTransaction transaction) {
        processTransaction(transaction);
    }
}
