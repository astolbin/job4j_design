package ru.job4j.lsp.food;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void route(Food food) {
        route(food, LocalDate.now());
    }

    public void route(Food food, LocalDate date) {
        for (Store store : stores) {
            if (store.accept(food, expiryPercent(food, date))) {
                return;
            }
        }

        throw new IllegalStateException("Route fail");
    }

    public void resort(LocalDate date) {
        stores.stream()
                .flatMap(store -> store.extract().stream())
                .forEach(food -> route(food, date));
    }

    private double expiryPercent(Food food, LocalDate now) {
        long allDays = food.getExpiryDate().toEpochDay() - food.getCreateDate().toEpochDay();
        long daysFromRelease = now.toEpochDay() - food.getCreateDate().toEpochDay();
        return (double) daysFromRelease / allDays * 100;
    }
}
