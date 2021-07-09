package ru.job4j.lsp;

public class Shop extends StoreFood {
    @Override
    public boolean add(Food food) {
        if (check(food)) {
            if (expiryPercent(food) > 75.0) {
                food.setDiscount(15);
            }
            foodList.add(food);
            return true;
        }
        return false;
    }

    @Override
    protected boolean check(Food food) {
        return expiryPercent(food) >= 25.0 && expiryPercent(food) < 100.0;
    }
}
