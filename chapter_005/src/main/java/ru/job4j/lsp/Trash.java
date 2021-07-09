package ru.job4j.lsp;

public class Trash extends StoreFood {
    @Override
    public boolean checkExpiry(double expiryPercent) {
        return expiryPercent >= 100.0;
    }
}
