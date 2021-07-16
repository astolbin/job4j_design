package ru.job4j.isp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MenuTest {
    private Item menu;

    @Before
    public void init() {
        menu = new MenuItem("0.", "Меню", new MenuAction("Выполнить Меню"));
        Item one = new MenuItem("1.", "Задача", new MenuAction("Выполняется задача 1."));
        Item oneOne = new MenuItem("1.1.", "Задача", new MenuAction("Выполняется задача 1.1."));
        Item oneOneOne = new MenuItem("1.1.1.", "Задача", new MenuAction("Выполняется задача 1.1.1."));
        Item oneOneTwo = new MenuItem("1.1.2.", "Задача", new MenuAction("Выполняется задача 1.1.2."));
        Item oneTwo = new MenuItem("1.2.", "Задача", new MenuAction("Выполняется задача 1.2."));

        menu.addChild(one);
        one.addChild(oneOne);
        one.addChild(oneTwo);
        oneOne.addChild(oneOneOne);
        oneOne.addChild(oneOneTwo);
    }

    @Test
    public void whenSuccessSearchMenuItem1Level() {
        Action expected = new MenuAction("Выполняется задача 1.");
        Action rsl = new ItemSearch().get(menu, "1.").getAction();
        Assert.assertEquals(expected, rsl);
    }

    @Test
    public void whenSuccessSearchMenuItem2Level() {
        Action expected = new MenuAction("Выполняется задача 1.1.");
        Action rsl = new ItemSearch().get(menu, "1.1.").getAction();
        Assert.assertEquals(expected, rsl);
    }

    @Test
    public void whenSuccessSearchMenuItem3Level() {
        Action expected = new MenuAction("Выполняется задача 1.1.1.");
        Action rsl = new ItemSearch().get(menu, "1.1.1.").getAction();
        Assert.assertEquals(expected, rsl);
    }

    @Test(expected = NullPointerException.class)
    public void whenFailSearchMenu() {
        new ItemSearch().get(menu, "1.4.").getAction().execute();
    }

    @Test
    public void whenShowMenu() {
        Output output = new StubOutput();
        output.show(menu);
        String rsl = output.toString();
        String expected = " Задача 1. " + System.lineSeparator()
                + "---- Задача 1.1. " + System.lineSeparator()
                + "-------- Задача 1.1.1. " + System.lineSeparator()
                + "-------- Задача 1.1.2. " + System.lineSeparator()
                + "---- Задача 1.2. " + System.lineSeparator();
        Assert.assertEquals(expected, rsl);
    }
}