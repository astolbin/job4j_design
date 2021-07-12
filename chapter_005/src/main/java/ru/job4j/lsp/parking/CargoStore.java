package ru.job4j.lsp.parking;

import java.util.List;
import java.util.function.Predicate;

public class CargoStore implements Store {
    public CargoStore(int cellsCount) {
    }

    @Override
    public boolean place(Car car) {
        return false;
    }

    @Override
    public List<Car> findByFilter(Predicate<Car> filter) {
        return null;
    }
}
