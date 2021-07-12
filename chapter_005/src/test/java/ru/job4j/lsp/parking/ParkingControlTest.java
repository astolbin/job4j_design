package ru.job4j.lsp.parking;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ParkingControlTest {
    Store cargoStore;
    Store passengerStore;
    Parking parkingController;

    @Before
    public void setUp() {
        cargoStore = new CargoStore(4);
        passengerStore = new PassengerStore(3);
        parkingController = new ParkingControl(List.of(cargoStore, passengerStore));
    }

    @Test
    public void whenAddCargoTrack() {
        Car cargoTrack = new CargoTrack(3);

        assertTrue(parkingController.route(cargoTrack));
        assertEquals(cargoTrack, cargoStore.findByFilter(car -> true).get(0));
        assertEquals(0, passengerStore.findByFilter(car -> true).size());
    }

    @Test
    public void whenAddPassengerCar() {
        Car passengerCar = new PassengerCar();

        assertTrue(parkingController.route(passengerCar));
        assertEquals(passengerCar, passengerStore.findByFilter(car -> true).get(0));
        assertEquals(0, cargoStore.findByFilter(car -> true).size());
    }

    @Test
    public void whenAddCargoTrackToPassengerStore() {
        Car cargoTrack1 = new CargoTrack(3);
        Car cargoTrack2 = new CargoTrack(3);

        assertTrue(parkingController.route(cargoTrack1));
        assertTrue(parkingController.route(cargoTrack2));
        assertEquals(cargoTrack1, cargoStore.findByFilter(car -> true).get(0));
        assertEquals(cargoTrack2, passengerStore.findByFilter(car -> true).get(0));
    }

    @Test
    public void whenPlaceIsOver() {
        Car cargoTrack1 = new CargoTrack(3);
        Car cargoTrack2 = new CargoTrack(3);
        Car passengerCar = new PassengerCar();

        assertTrue(parkingController.route(cargoTrack1));
        assertTrue(parkingController.route(cargoTrack2));
        assertFalse(parkingController.route(passengerCar));
    }

    @Test(expected = IllegalStateException.class)
    public void whenEmptyStoreList() {
        Parking parkingController = new ParkingControl(List.of());
        Car cargoTrack = new CargoTrack(3);
        parkingController.route(cargoTrack);
    }
}