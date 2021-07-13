package ru.job4j.lsp.parking;

public class CargoTrack implements Car {
    private final int cellsCount;

    public CargoTrack(int cellsCount) {
        this.cellsCount = cellsCount;
    }

    @Override
    public int getCellsCount() {
        return cellsCount;
    }
}
