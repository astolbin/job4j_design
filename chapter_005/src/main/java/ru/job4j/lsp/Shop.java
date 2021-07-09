package ru.job4j.lsp;

public class Shop extends StoreFood {
    @Override
    public boolean accept(Food food, double expiryPercent) {
        if (super.accept(food, expiryPercent)) {
            if (expiryPercent > 75.0) {
                food.setDiscount(15);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean checkExpiry(double expiryPercent) {
        return expiryPercent >= 25.0 && expiryPercent < 100.0;
    }
}
