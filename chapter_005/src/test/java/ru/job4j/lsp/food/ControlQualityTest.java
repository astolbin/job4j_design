package ru.job4j.lsp.food;

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
    public void whenRouteToWarehouse() {
        Food apple = new Apple(
                "Green apple",
                LocalDate.now().plusDays(20),
                LocalDate.now().minusDays(5),
                100.0,
                0
        );

        controller.route(apple);

        assertEquals(apple, warehouse.getByFilter(filter).get(0));
        assertEquals(0, shop.getByFilter(filter).size());
        assertEquals(0, trash.getByFilter(filter).size());
    }

    @Test
    public void whenRouteToShop() {
        Food milk = new Milk(
                "Village milk",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(10),
                100.0,
                0
        );

        controller.route(milk);

        assertEquals(milk, shop.getByFilter(filter).get(0));
        assertEquals(0, shop.getByFilter(filter).get(0).getDiscount(), 0.1);
        assertEquals(0, warehouse.getByFilter(filter).size());
        assertEquals(0, trash.getByFilter(filter).size());
    }

    @Test
    public void whenRouteToShopWithDiscount() {
        double price = 100.0;
        Food milk = new Milk(
                "Village milk",
                LocalDate.now().plusDays(3),
                LocalDate.now().minusDays(20),
                price,
                0
        );

        controller.route(milk);

        Food routedFood = shop.getByFilter(filter).get(0);
        double priceWithDiscount = price * (100 - routedFood.getDiscount()) / 100;

        assertEquals(milk, routedFood);
        assertEquals(priceWithDiscount, routedFood.getPrice(), 0.1);
        assertEquals(0, warehouse.getByFilter(filter).size());
        assertEquals(0, trash.getByFilter(filter).size());
    }

    @Test
    public void whenRouteToTrash() {
        Food tomato = new Tomato(
                "Red tomato",
                LocalDate.now().minusDays(3),
                LocalDate.now().minusDays(20),
                100.0,
                0
        );

        controller.route(tomato);

        assertEquals(tomato, trash.getByFilter(filter).get(0));
        assertEquals(0, warehouse.getByFilter(filter).size());
        assertEquals(0, shop.getByFilter(filter).size());
    }

    @Test(expected = IllegalStateException.class)
    public void whenRouteFail() {
        Food apple = new Apple(
                "Green apple",
                LocalDate.now().plusDays(20),
                LocalDate.now().minusDays(10),
                100.0,
                0
        );

        ControlQuality emptyController = new ControlQuality(List.of());
        emptyController.route(apple);
    }

    @Test
    public void whenResort() {
        Food apple = new Apple(
                "Green apple",
                LocalDate.now().plusDays(20),
                LocalDate.now().minusDays(5),
                100.0,
                0
        );

        controller.route(apple);
        controller.resort(LocalDate.now().plusDays(10));

        assertEquals(apple, shop.getByFilter(filter).get(0));
        assertEquals(0, shop.getByFilter(filter).get(0).getDiscount(), 0.1);
        assertEquals(0, warehouse.getByFilter(filter).size());
        assertEquals(0, trash.getByFilter(filter).size());
    }
}