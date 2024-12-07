package org.poo.model.merchant;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Merchant> merchants;
    public Category(String name) {
        this.name = name;
        this.merchants = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }

    public void addMerchant(Merchant merchant) {
        merchants.add(merchant);
    }
}
