package ru.job4j.lsp.food;

import java.time.LocalDate;

public class Apple extends Food {

    public Apple(
            String name,
            LocalDate expiryDate,
            LocalDate createDate,
            double price,
            double discount
    ) {
        super(name, expiryDate, createDate, price, discount);
    }
}
