package org.poo.model.transaction;

import org.poo.model.merchant.Merchant;
import org.poo.visitor.transaction.TransactionVisitor;

import java.util.Map;

public class SplitPaymentTransaction extends Transaction {
    private Merchant merchant;
    private String category;
    private Map<String, Double> participants; // Account IBAN to amount
    private String currency;

    public SplitPaymentTransaction(int timestamp, Merchant merchant,
                                   String category, Map<String, Double> participants, String currency) {
        super(timestamp);
        this.description = "SplitPayment";
        this.merchant = merchant;
        this.category = category;
        this.participants = participants;
        this.currency = currency;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public String getCategory() {
        return category;
    }

    public Map<String, Double> getParticipants() {
        return participants;
    }

    public String getCurrency() {
        return currency;
    }

    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
