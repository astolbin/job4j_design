package ru.job4j.lsp.parking;

import java.util.List;

public class ParkingControl implements Parking {
    public ParkingControl(List<Store> storeList) {
    }

    @Override
    public boolean route(Car car) {
        return false;
    }
}
