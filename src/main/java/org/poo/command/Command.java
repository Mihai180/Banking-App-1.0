package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public interface Command {
    void accept(CommandVisitor visitor);
}
