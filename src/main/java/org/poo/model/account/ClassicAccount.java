package org.poo.model.account;

import org.poo.model.user.User;

public class ClassicAccount extends Account {
    public ClassicAccount(String iban, User owner, String currency) {
        super(iban, owner, currency);
    }

    public String getAccountType() {
        return "classic";
    }
}
