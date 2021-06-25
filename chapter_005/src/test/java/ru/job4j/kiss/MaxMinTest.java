package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {
    MaxMin<Integer> maxMin = new MaxMin<>();
    List<Integer> list = List.of(2, 5, 1, 3, 8);

    @Test
    public void whenSearchMax() {
        int max = maxMin.max(list, Integer::compareTo);
        assertEquals(8, max);
    }

    @Test
    public void whenSearchMin() {
        int min = maxMin.min(list, Integer::compareTo);
        assertEquals(1, min);
    }

    @Test(expected=IllegalArgumentException.class)
    public void whenEmptyList() {
        maxMin.min(List.of(), Comparator.reverseOrder());
    }
}