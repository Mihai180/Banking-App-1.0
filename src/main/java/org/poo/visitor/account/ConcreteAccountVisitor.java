package org.poo.visitor.account;

import org.poo.model.account.ClassicAccount;
import org.poo.model.account.SavingsAccount;

public class ConcreteAccountVisitor implements AccountVisitor {
    private String accountInfo;
    private double totalBalance;

    public String getAccountInfo() {
        return accountInfo;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    @Override
    public void visit(SavingsAccount account) {
        // Collect account information
        accountInfo = "SavingsAccount IBAN: " + account.getIban()
                + ", Balance: " + account.getBalance()
                + ", Currency: " + account.getCurrency()
                + ", Interest Rate: " + account.getInterestRate();

        // Perform operations, e.g., calculate potential interest
        double potentialInterest = account.getBalance() * account.getInterestRate();
        totalBalance = account.getBalance() + potentialInterest;
    }

    @Override
    public void visit(ClassicAccount account) {
        // Collect account information
        accountInfo = "ClassicAccount IBAN: " + account.getIban()
                + ", Balance: " + account.getBalance()
                + ", Currency: " + account.getCurrency();

        // For ClassicAccount, total balance is just the current balance
        totalBalance = account.getBalance();
    }
}
