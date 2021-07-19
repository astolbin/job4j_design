package ru.job4j.lsp.food;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void route(Food food) {
        for (Store store : stores) {
            if (store.accept(food, expiryPercent(food))) {
                return;
            }
        }

        throw new IllegalStateException("Route fail");
    }

    private double expiryPercent(Food food) {
        long allDays = food.getExpiryDate().toEpochDay() - food.getCreateDate().toEpochDay();
        long daysFromRelease = LocalDate.now().toEpochDay() - food.getCreateDate().toEpochDay();
        return (double) daysFromRelease / allDays * 100;
    }
}
