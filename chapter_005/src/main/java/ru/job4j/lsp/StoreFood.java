package ru.job4j.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class StoreFood implements Store {
    protected final List<Food> foodList = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        if (check(food)) {
            foodList.add(food);
            return true;
        }
        return false;
    }

    protected double expiryPercent(Food food) {
        return (double) (LocalDate.now().until(food.getCreateDate(), ChronoUnit.DAYS))
                / (double) (food.getExpiryDate().until(food.getCreateDate(), ChronoUnit.DAYS))
                * 100;
    }

    @Override
    public List<Food> getFoodList() {
        return foodList;
    }

    protected abstract boolean check(Food food);
}
