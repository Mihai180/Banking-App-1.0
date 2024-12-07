package org.poo.visitor.command;

import org.poo.command.*;

public interface CommandVisitor {
    void visit(PrintUsersCommand command);
    void visit(AddAccountCommand command);
    void visit(AddFundsCommand command);
    void visit(CreateCardCommand command);
    void visit(NotImplementedCommand command);
    void visit(DeleteAccountCommand command);
}
