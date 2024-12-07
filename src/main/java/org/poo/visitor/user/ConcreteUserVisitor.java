package org.poo.visitor.user;

import org.poo.model.account.Account;
import org.poo.model.user.User;

import java.util.ArrayList;

public class ConcreteUserVisitor implements UserVisitor {
    private String userInfo;
    private ArrayList<String> accountSummaries;

    public ConcreteUserVisitor() {
        accountSummaries = new ArrayList<>();
    }

    public String getUserInfo() {
        return userInfo;
    }

    public ArrayList<String> getAccountSummaries() {
        return accountSummaries;
    }

    @Override
    public void visit(User user) {
        userInfo = "User: " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")";

        for (Account account : user.getAccounts()) {
            String accountSummary = "Account IBAN: " + account.getIban()
                    + ", Balance: " + account.getBalance()
                    + ", Currency: " + account.getCurrency()
                    + ", Type: " + account.getClass().getSimpleName();
            accountSummaries.add(accountSummary);
        }
    }
}
