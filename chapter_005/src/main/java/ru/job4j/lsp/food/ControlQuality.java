package ru.job4j.lsp.food;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void rout(Food food) {
        for (Store store : stores) {
            if (store.accept(food, expiryPercent(food))) {
                return;
            }
        }

        throw new IllegalStateException("Rout fail");
    }

    private double expiryPercent(Food food) {
        long allDays = food.getExpiryDate().toEpochDay() - food.getCreateDate().toEpochDay();
        long daysFromRelease = LocalDate.now().toEpochDay() - food.getCreateDate().toEpochDay();
        return (double) daysFromRelease / (double) allDays * 100;
    }
}
