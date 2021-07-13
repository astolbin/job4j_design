package ru.job4j.lsp.parking;

public class PassengerCar implements Car {
    @Override
    public int getCellsCount() {
        return 1;
    }
}
