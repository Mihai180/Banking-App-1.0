package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public interface Command {
    /**
     *
     * @param visitor
     */
    void accept(CommandVisitor visitor);
}
