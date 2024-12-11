package org.poo.visitor.transaction;

import org.poo.model.transaction.*;

public interface TransactionVisitor {
    void visit(AccountCreationTransaction transaction);
    void visit(CardCreationTransaction transaction);
    void visit(SendMoneyTransaction transaction);
    void visit(CardPaymentTransaction transaction);
    void visit(InsufficientFundsTransaction transaction);
    void visit(CardDeletionTransaction transaction);
    void visit(MinimumAmountOfFundsTransaction transaction);
    void visit(FrozenCardTransaction transaction);

    void visit(BankTransferTransaction transaction);
    void visit(InterestRateChangeTransaction transaction);
    void visit(InterestTransaction transaction);
    void visit(MinBalanceSettingTransaction transaction);
    void visit(SplitPaymentTransaction transaction);
    void visit(ErrorTransaction transaction);
}
