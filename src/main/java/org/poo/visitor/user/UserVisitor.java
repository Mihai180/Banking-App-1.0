package org.poo.visitor.user;

import org.poo.model.user.User;

public interface UserVisitor {
    void visit(User user);
}
