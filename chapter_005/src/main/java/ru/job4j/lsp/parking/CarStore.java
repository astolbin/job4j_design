package ru.job4j.lsp.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class CarStore implements Store {
    private final List<Car> carList = new ArrayList<>();
    private int cellsCapacity;

    public CarStore(int cellsCapacity) {
        this.cellsCapacity = cellsCapacity;
    }

    @Override
    public boolean place(Car car) {
        if (car.getCellsCount() <= cellsCapacity) {
            carList.add(car);
            cellsCapacity = cellsCapacity - car.getCellsCount();
            return true;
        }

        return false;
    }

    @Override
    public List<Car> findByFilter(Predicate<Car> filter) {
        return carList.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }
}
