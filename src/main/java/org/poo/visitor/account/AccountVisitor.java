package org.poo.visitor.account;

import org.poo.model.account.ClassicAccount;
import org.poo.model.account.SavingsAccount;

public interface AccountVisitor {
    void visit(SavingsAccount account);
    void visit(ClassicAccount account);
}
