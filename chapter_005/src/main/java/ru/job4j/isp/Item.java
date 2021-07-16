package ru.job4j.isp;

import java.util.List;

public interface Item {
    List<Item> getChildren();
    void addChild(Item child);
    String getNumber();
    String getName();
    Action getAction();
}
