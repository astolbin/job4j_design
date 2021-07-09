package ru.job4j.lsp;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ControlQualityTest {
    Store warehouse;
    Store shop;
    Store trash;
    ControlQuality controller;

    @Before
    public void setUp() {
        warehouse = new Warehouse();
        shop = new Shop();
        trash = new Trash();
        controller = new ControlQuality();

        controller.addStore(warehouse);
        controller.addStore(shop);
        controller.addStore(trash);
    }

    @Test
    public void whenRoutToWarehouse() {
        Food apple = new Apple(
                "Red apple",
                LocalDate.of(2021, 8, 1),
                LocalDate.of(2021, 7, 5),
                100.0,
                0
        );

        controller.rout(apple);

        assertEquals(apple, warehouse.getFoodList().get(0));
        assertEquals(0, shop.getFoodList().size());
        assertEquals(0, trash.getFoodList().size());
    }

    @Test
    public void whenRoutToShop() {
        Food apple = new Apple(
                "Green apple",
                LocalDate.of(2021, 7, 21),
                LocalDate.of(2021, 7, 1),
                100.0,
                0
        );

        controller.rout(apple);

        assertEquals(apple, shop.getFoodList().get(0));
        assertEquals(0, shop.getFoodList().get(0).getDiscount(), 0.1);
        assertEquals(0, warehouse.getFoodList().size());
        assertEquals(0, trash.getFoodList().size());
    }

    @Test
    public void whenRoutToShopWithDiscount() {
        Food apple = new Apple(
                "Green apple",
                LocalDate.of(2021, 7, 11),
                LocalDate.of(2021, 7, 1),
                100.0,
                0
        );

        controller.rout(apple);

        assertEquals(apple, shop.getFoodList().get(0));
        assertEquals(15, shop.getFoodList().get(0).getDiscount(), 0.1);
        assertEquals(0, warehouse.getFoodList().size());
        assertEquals(0, trash.getFoodList().size());
    }

    @Test
    public void whenRoutToTrash() {
        Food apple = new Apple(
                "Yellow apple",
                LocalDate.of(2021, 7, 9),
                LocalDate.of(2021, 7, 1),
                100.0,
                0
        );

        controller.rout(apple);

        assertEquals(apple, trash.getFoodList().get(0));
        assertEquals(0, warehouse.getFoodList().size());
        assertEquals(0, shop.getFoodList().size());
    }

    @Test(expected = IllegalStateException.class)
    public void whenRoutFail() {
        Food apple = new Apple(
                "Yellow apple",
                LocalDate.of(2021, 8, 1),
                LocalDate.of(2021, 7, 5),
                100.0,
                0
        );

        ControlQuality emptyController = new ControlQuality();
        emptyController.rout(apple);
    }
}