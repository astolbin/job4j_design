package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {
    private SimpleMap<String, Integer> map;

    @Before
    public void before() {
        map = new SimpleMap<>();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateEmpty() {
        map.iterator().next();
    }

    @Test
    public void whenIterateSuccess() {
        map.insert("first", 1);
        Iterator<Integer> it = map.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenKeyNotFound() {
        assertNull(map.get("first"));
    }

    @Test
    public void whenKeyFound() {
        var rsl = map.insert("first", 1);

        assertTrue(rsl);
        assertThat(map.get("first"), is(1));
    }

    @Test
    public void whenCollision() {
        map.insert("first", 1);

        assertFalse(map.insert("first", 1));
    }

    @Test
    public void whenDeleteNotFound() {
        assertFalse(map.delete("first"));
    }

    @Test
    public void whenDeleteSuccess() {
        map.insert("first", 1);

        assertTrue(map.delete("first"));
        assertNull(map.get("first"));
    }

    @Test
    public void whenGrowMap() {
        map.insert("123", 0);
        map.insert("234", 1);
        map.insert("345", 2);
        map.insert("456", 3);
        map.insert("567", 4);
        map.insert("678", 5);
        map.insert("789", 6);

        assertThat(map.get("789"), is(6));
    }
}