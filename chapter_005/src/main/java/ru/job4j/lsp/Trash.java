package ru.job4j.lsp;

public class Trash extends StoreFood {
    @Override
    protected boolean check(Food food) {
        return expiryPercent(food) >= 100.0;
    }
}
