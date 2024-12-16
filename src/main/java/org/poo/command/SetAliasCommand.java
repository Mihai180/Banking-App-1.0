package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public final class SetAliasCommand implements Command {
    private final String email;
    private final String alias;
    private final String account;

    public SetAliasCommand(final String email, final String alias, final String account) {
        this.email = email;
        this.alias = alias;
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public String getAlias() {
        return alias;
    }

    public String getAccount() {
        return account;
    }

    @Override
    public void accept(final CommandVisitor visitor) {
        visitor.visit(this);
    }
}
