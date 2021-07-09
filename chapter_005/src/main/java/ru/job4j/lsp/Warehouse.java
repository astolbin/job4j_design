package ru.job4j.lsp;

public class Warehouse extends StoreFood {
    @Override
    protected boolean check(Food food) {
        return expiryPercent(food) < 25.0;
    }
}
