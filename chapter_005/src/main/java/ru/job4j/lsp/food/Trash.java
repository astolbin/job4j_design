package ru.job4j.lsp.food;

public class Trash extends StoreFood {
    @Override
    public boolean checkExpiry(double expiryPercent) {
        return expiryPercent >= 100.0;
    }
}
