package ru.job4j.lsp;

import java.time.LocalDate;

public class Food {
    protected String name;
    protected LocalDate expiryDate;
    protected LocalDate createDate;
    protected double price;
    protected double discount;

    public String getName() {
        return name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
