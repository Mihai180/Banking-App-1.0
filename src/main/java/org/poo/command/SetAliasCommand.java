package org.poo.command;

import org.poo.visitor.command.CommandVisitor;

public class SetAliasCommand implements Command {
    private String email;
    private String alias;
    private String account;

    public SetAliasCommand(String email, String alias, String account) {
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

    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }
}
