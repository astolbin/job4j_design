package ru.job4j.lsp.parking;

import java.util.List;
import java.util.function.Predicate;

public class PassengerStore implements Store{
    public PassengerStore(int cellsCount) {
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
