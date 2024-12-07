package org.poo.model.merchant;

public class Merchant {
    private String name;
    private Category category;

    public Merchant(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
