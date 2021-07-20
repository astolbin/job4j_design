package ru.job4j.lsp.food;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class StoreFood implements Store {
    private final List<Food> foodList = new ArrayList<>();

    public abstract boolean checkExpiry(double expiryPercent);

    @Override
    public boolean accept(Food food, double expiryPercent) {
        if (checkExpiry(expiryPercent)) {
            foodList.add(food);
            return true;
        }
        return false;
    }

    @Override
    public List<Food> getByFilter(Predicate<Food> predicate) {
        return foodList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> extract() {
        var rsl = new ArrayList<>(foodList);
        foodList.clear();
        return rsl;
    }
}
