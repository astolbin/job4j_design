package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArray<E> array = new SimpleArray<>();

    public void add(E value) {
        if (!hasEquals(value)) {
            array.add(value);
        }
    }

    private boolean hasEquals(E value) {
        boolean rsl = false;

        for (E current : array) {
            if (Objects.equals(value, current)) {
                rsl = true;
                break;
            }
        }

        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }
}
