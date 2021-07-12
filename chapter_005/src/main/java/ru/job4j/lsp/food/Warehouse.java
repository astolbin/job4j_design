package ru.job4j.lsp.food;

public class Warehouse extends StoreFood {
    @Override
    public boolean checkExpiry(double expiryPercent) {
        return expiryPercent < 25.0;
    }
}
