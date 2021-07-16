package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

public class MenuItem implements Item {
    private final Action action;
    private final String name;
    private final String number;
    private final List<Item> childrenMenuList = new ArrayList<>();

    public MenuItem(String number, String name, Action action) {
        this.action = action;
        this.name = name;
        this.number = number;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public List<Item> getChildren() {
        return childrenMenuList;
    }

    @Override
    public void addChild(Item child) {
        childrenMenuList.add(child);
    }
}
