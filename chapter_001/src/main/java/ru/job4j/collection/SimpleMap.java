package ru.job4j.collection;

import java.util.*;

public class SimpleMap<K, V> implements Iterable<V> {
    private final static float LOAD_FACTOR = 0.5f;
    private final static int DEFAULT_CAPACITY = 6;
    private Item<K, V>[] table = new Item[DEFAULT_CAPACITY];
    private int size = 0;

    public boolean insert(K key, V value) {
        boolean rsl = false;

        growTable();

        int index = getIndex(key);

        if (table[index] == null) {
            table[index] = new Item<>(key, value);
            rsl = true;
            size++;
        }

        return rsl;
    }

    public V get(K key) {
        V rsl = null;

        int index = getIndex(key);

        if (table[index] != null) {
            Item<K, V> item = table[index];
            if (Objects.equals(key, item.key)) {
                rsl = item.value;
            }
        }

        return rsl;
    }

    public boolean delete(K key) {
        boolean rsl = false;

        int index = getIndex(key);

        if (table[index] != null) {
            Item<K, V> item = table[index];
            if (Objects.equals(key, item.key)) {
                table[index] = null;
                rsl = true;
                size--;
            }
        }

        return rsl;
    }

    private void growTable() {
        int threshold = (int) (table.length * LOAD_FACTOR);

        if (size >= threshold) {
            int newCapacity = table.length + DEFAULT_CAPACITY;
            table = Arrays.copyOf(table, newCapacity);
        }
    }

    private int getIndex(K key) {
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

        return (table.length - 1) & hash;
    }

    @Override
    public Iterator<V> iterator() {
        return new Itr();
    }

    static class Item<K, V> {
        final K key;
        final V value;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private class Itr implements Iterator<V> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            while (cursor < table.length && table[cursor] == null) {
                cursor++;
            }

            return cursor < table.length;
        }

        @Override
        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return table[cursor++].value;
        }
    }
}
