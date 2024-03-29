package ru.job4j.lsp.food;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    boolean accept(Food food, double expiryPercent);
    List<Food> getByFilter(Predicate<Food> predicate);
    List<Food> extract();
}
