package ru.job4j.lsp;

import java.time.LocalDate;

public class Tomato extends Food {

    public Tomato(
            String name,
            LocalDate expiryDate,
            LocalDate createDate,
            double price,
            double discount
    ) {
        super(name, expiryDate, createDate, price, discount);
    }
}
