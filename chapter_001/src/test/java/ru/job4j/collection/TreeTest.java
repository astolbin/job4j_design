package ru.job4j.collection;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenAddDuplicate() {
        Tree<Integer> tree = new Tree<>(1);
        assertThat(tree.add(1, 2), is(true));
        assertThat(tree.add(1, 2), is(false));
    }

    @Test
    public void whenIsNotBinary1() {
        Tree<Integer> tree = new Tree<>(1);

        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);

        assertThat(tree.isBinary(), is(false));
    }

    @Test
    public void whenIsNotBinary2() {
        Tree<Integer> tree = new Tree<>(1);

        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(3, 5);
        tree.add(3, 6);

        assertThat(tree.isBinary(), is(false));
    }

    @Test
    public void whenIsBinary() {
        Tree<Integer> tree = new Tree<>(1);

        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(3, 5);
        tree.add(5, 6);
        tree.add(5, 7);

        assertThat(tree.isBinary(), is(true));
    }
}