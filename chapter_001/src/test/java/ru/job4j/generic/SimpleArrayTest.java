package ru.job4j.generic;

import org.junit.Test;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    @Test(expected = IllegalArgumentException.class)
    public void whenWrongSize() {
        new SimpleArray<Integer>(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        var array = new SimpleArray<String>(0);
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutOfBound() {
        var array = new SimpleArray<String>(10);
        array.add("first");
        assertThat(array.get(0), is("first"));
        array.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddOutOfBound() {
        var array = new SimpleArray<String>(1);
        array.add("first");
        assertThat(array.get(0), is("first"));
        array.add("second");
    }

    @Test
    public void whenSetSuccess() {
        var array = new SimpleArray<String>(1);
        array.add("first");
        assertThat(array.get(0), is("first"));
        array.set(0, "second");
        assertThat(array.get(0), is("second"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetOutOfBound() {
        var array = new SimpleArray<String>(10);
        array.add("first");
        array.set(1, "second");
    }

    @Test
    public void whenRemoveSuccess() {
        var array = new SimpleArray<String>(10);
        array.add("first");
        array.add("second");
        array.add("third");
        array.remove(1);
        assertThat(array.get(0), is("first"));
        assertThat(array.get(1), is("third"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveOutOfBound() {
        var array = new SimpleArray<String>(10);
        array.add("first");
        array.remove(1);
    }

    @Test
    public void whenGetSuccessFromIt() {
        var array = new SimpleArray<String>(1);
        array.add("first");
        assertThat(array.iterator().next(), is("first"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        var array = new SimpleArray<String>(10);
        array.iterator().next();
    }
}