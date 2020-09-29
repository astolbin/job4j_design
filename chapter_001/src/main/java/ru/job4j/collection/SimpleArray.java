package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private final static int DEFAULT_CAPACITY = 5;
    private Object[] container = new Object[DEFAULT_CAPACITY];
    private int size = 0;
    private int modCount = 0;

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        if (size == container.length) {
            container = growContainer();
        }
        container[size++] = model;
        modCount++;
    }

    private Object[] growContainer() {
        int newCapacity = container.length + DEFAULT_CAPACITY;
        return Arrays.copyOf(container, newCapacity);
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor;
        int expectedModCount;

        public Itr() {
            this.cursor = 0;
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            return (T) container[cursor++];
        }
    }
}
