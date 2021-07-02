package ru.job4j.spr;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}
