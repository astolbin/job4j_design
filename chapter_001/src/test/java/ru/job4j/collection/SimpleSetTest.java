package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    @Test(expected = NoSuchElementException.class)
    public void whenEmptySet() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.iterator().next();
    }

    @Test
    public void whenAddSameValue() {
        SimpleSet<Integer> set = new SimpleSet<>();

        set.add(1);
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();

        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }
}