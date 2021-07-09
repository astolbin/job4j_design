package ru.job4j.lsp;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.time.LocalDate;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

public class ControlQualityTest {
    Store warehouse;
    Store shop;
    Store trash;
    ControlQuality controller;
    Predicate<Food> filter;

    @Before
    public void setUp() {
        warehouse = new Warehouse();
        shop = new Shop();
        trash = new Trash();
        controller = new ControlQuality(List.of(warehouse, shop, trash));
        filter = food -> true;
    }

    @Test
    public void whenRoutToWarehouse() {
        Food apple = new Apple(
                "Green apple",
                LocalDate.now().plusDays(20),
                LocalDate.now().minusDays(5),
                100.0,
                0
        );

        controller.rout(apple);

        assertEquals(apple, warehouse.getByFilter(filter).get(0));
        assertEquals(0, shop.getByFilter(filter).size());
        assertEquals(0, trash.getByFilter(filter).size());
    }

    @Test
    public void whenRoutToShop() {
        Food milk = new Milk(
                "Village milk",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(10),
                100.0,
                0
        );

        controller.rout(milk);

        assertEquals(milk, shop.getByFilter(filter).get(0));
        assertEquals(0, shop.getByFilter(filter).get(0).getDiscount(), 0.1);
        assertEquals(0, warehouse.getByFilter(filter).size());
        assertEquals(0, trash.getByFilter(filter).size());
    }

    @Test
    public void whenRoutToShopWithDiscount() {
        double price = 100.0;
        Food milk = new Milk(
                "Village milk",
                LocalDate.now().plusDays(3),
                LocalDate.now().minusDays(20),
                price,
                0
        );

        controller.rout(milk);

        Food routedFood = shop.getByFilter(filter).get(0);
        double priceWithDiscount = price * (100 - routedFood.getDiscount()) / 100;

        assertEquals(milk, routedFood);
        assertEquals(priceWithDiscount, routedFood.getPrice(), 0.1);
        assertEquals(0, warehouse.getByFilter(filter).size());
        assertEquals(0, trash.getByFilter(filter).size());
    }

    @Test
    public void whenRoutToTrash() {
        Food tomato = new Tomato(
                "Red tomato",
                LocalDate.now().minusDays(3),
                LocalDate.now().minusDays(20),
                100.0,
                0
        );

        controller.rout(tomato);

        assertEquals(tomato, trash.getByFilter(filter).get(0));
        assertEquals(0, warehouse.getByFilter(filter).size());
        assertEquals(0, shop.getByFilter(filter).size());
    }

    @Test(expected = IllegalStateException.class)
    public void whenRoutFail() {
        Food apple = new Apple(
                "Green apple",
                LocalDate.now().plusDays(20),
                LocalDate.now().minusDays(10),
                100.0,
                0
        );

        ControlQuality emptyController = new ControlQuality(List.of());
        emptyController.rout(apple);
    }
}