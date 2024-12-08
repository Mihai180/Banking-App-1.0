package org.poo.model.transaction;

import org.poo.model.merchant.Merchant;
import org.poo.visitor.transaction.TransactionVisitor;

public class PaymentTransaction extends Transaction {
    private String merchant;
    private String category;
    private String currency;

    public PaymentTransaction(int timestamp, String description, double amount, String status,
                              String merchant, String category, String currency) {
        super(timestamp, description, amount, status);
        this.merchant = merchant;
        this.category = category;
        this.currency = currency;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getCategory() {
        return category;
    }

    public String getCurrency() {
        return currency;
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
