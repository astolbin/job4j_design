package ru.job4j.lsp.parking;

public class CargoStore extends CarStore {

    public CargoStore(int cellsCapacity) {
        super(cellsCapacity);
    }

    @Override
    public boolean place(Car car) {
        return car.getCellsCount() > 1
                && super.place(car);
    }
}
