package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private static final Object[] EMPTY = {};
    private final Object[] items;
    private int count = 0;

    public SimpleArray(int size) {
        if (size > 0) {
            items = new Object[size];
        } else if (size == 0) {
            items = EMPTY;
        } else {
            throw new IllegalArgumentException("Illegal Size: "+ size);
        }
    }

    public void add(T model) {
        Objects.checkIndex(count, items.length);
        items[count++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, count);
        items[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, count);
        System.arraycopy(items, index + 1, items, index, items.length - (index + 1));
        items[count--] = null;
    }

    public T get(int index) {
        Objects.checkIndex(index, count);
        return (T) items[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < count;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return (T) items[cursor++];
        }
    }
}
