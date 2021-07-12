package ru.job4j.lsp.parking;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    boolean place(Car car);
    List<Car> findByFilter(Predicate<Car> filter);
}
