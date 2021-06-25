package ru.job4j.kiss;

import java.util.List;
import java.util.Comparator;

public class MaxMin<T> {
    public T max(List<T> value, Comparator<T> comparator) {
        return find(value, comparator);
    }

    public T min(List<T> value, Comparator<T> comparator) {
        return find(value, comparator.reversed());
    }

    private T find(List<T> items, Comparator<T> comparator) {
        checkList(items);

        T result = items.get(0);
        for (T value : items) {
            if (comparator.compare(value, result) > 0) {
                result = value;
            }
        }
        return result;
    }

    private void checkList(List<T> items) {
        if (items.isEmpty()) {
            throw new  IllegalArgumentException("Empty list");
        }
    }
}
