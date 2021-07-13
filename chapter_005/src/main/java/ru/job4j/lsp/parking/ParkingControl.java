package ru.job4j.lsp.parking;

import java.util.List;

public class ParkingControl implements Parking {
    private final List<Store> storeList;

    public ParkingControl(List<Store> storeList) {
        this.storeList = storeList;
    }

    @Override
    public boolean route(Car car) {
        if (storeList.size() == 0) {
            throw new IllegalStateException("Empty store list");
        }

        for (Store store : storeList) {
            if (store.place(car)) {
                return true;
            }
        }

        return false;
    }
}
