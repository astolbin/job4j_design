package ru.job4j.lsp;

import java.time.LocalDate;

public class Apple extends Food {


    public Apple(
            String name,
            LocalDate expiryDate,
            LocalDate createDate,
            double price,
            double discount
    ) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }
}
