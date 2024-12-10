package org.poo.visitor.transaction;

import org.poo.model.transaction.*;

public interface TransactionVisitor {
    void visit(AccountCreationTransaction transaction);
    void visit(BankTransferTransaction transaction);
    void visit(CardCreationTransaction transaction);
    void visit(CardDeletionTransaction transaction);
    void visit(InterestRateChangeTransaction transaction);
    void visit(InterestTransaction transaction);
    void visit(MinBalanceSettingTransaction transaction);
    void visit(PaymentTransaction transaction);
    void visit(SplitPaymentTransaction transaction);
    void visit(ErrorTransaction transaction);


    //void visit(AccountCreationTransaction transaction);
    void visit(SendMoneyTransaction transaction);

}
