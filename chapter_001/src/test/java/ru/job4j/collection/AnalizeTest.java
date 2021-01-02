package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizeTest {
    @Test
    public void whenEmptyLists() {
        var analize = new Analize();
        var info = analize.diff(new ArrayList<>(), new ArrayList<>());

        assertThat(info.added, is(0));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(0));
    }

    @Test
    public void whenEmptyPrevious() {
        var analize = new Analize();
        List<Analize.User> previous = new ArrayList<>();
        List<Analize.User> current = List.of(
                new Analize.User(0, "User1"),
                new Analize.User(1, "User2"),
                new Analize.User(2, "User3")
        );
        var info = analize.diff(previous, current);

        assertThat(info.added, is(3));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(0));
    }

    @Test
    public void whenEmptyCurrent() {
        var analize = new Analize();
        List<Analize.User> previous = List.of(
                new Analize.User(0, "User1"),
                new Analize.User(1, "User2"),
                new Analize.User(2, "User3")
        );
        List<Analize.User> current = new ArrayList<>();
        var info = analize.diff(previous, current);

        assertThat(info.added, is(0));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(3));
    }

    @Test
    public void whenAddUsers() {
        var analize = new Analize();
        List<Analize.User> previous = List.of(
                new Analize.User(0, "User1"),
                new Analize.User(1, "User2"),
                new Analize.User(2, "User3")
        );
        List<Analize.User> current = List.of(
                new Analize.User(0, "User1"),
                new Analize.User(1, "User2"),
                new Analize.User(2, "User3"),
                new Analize.User(3, "User4"),
                new Analize.User(4, "User5")
        );

        var result = analize.diff(previous, current);

        assertThat(result.added, is(2));
        assertThat(result.changed, is(0));
        assertThat(result.deleted, is(0));
    }

    @Test
    public void whenChangeUsers() {
        var analize = new Analize();
        List<Analize.User> previous = List.of(
                new Analize.User(0, "User1"),
                new Analize.User(1, "User2"),
                new Analize.User(2, "User3"),
                new Analize.User(3, "User4")
        );
        List<Analize.User> current = List.of(
                new Analize.User(0, "User1"),
                new Analize.User(1, "User2"),
                new Analize.User(2, "User33"),
                new Analize.User(3, "User44")
        );

        var result = analize.diff(previous, current);

        assertThat(result.added, is(0));
        assertThat(result.changed, is(2));
        assertThat(result.deleted, is(0));
    }

    @Test
    public void whenDeleteUsers() {
        var analize = new Analize();
        List<Analize.User> previous = List.of(
                new Analize.User(0, "User1"),
                new Analize.User(1, "User2"),
                new Analize.User(2, "User3"),
                new Analize.User(3, "User4")
        );
        List<Analize.User> current = List.of(
                new Analize.User(0, "User1"),
                new Analize.User(1, "User2")
        );

        var result = analize.diff(previous, current);

        assertThat(result.added, is(0));
        assertThat(result.changed, is(0));
        assertThat(result.deleted, is(2));
    }

    @Test
    public void whenAllActions() {
        var analize = new Analize();
        List<Analize.User> previous = List.of(
                new Analize.User(0, "User1"),
                new Analize.User(1, "User22"),
                new Analize.User(2, "User3"),
                new Analize.User(3, "User4")
        );
        List<Analize.User> current = List.of(
                new Analize.User(0, "User1"),
                new Analize.User(1, "User2"),
                new Analize.User(3, "User4"),
                new Analize.User(5, "User5")
        );

        var result = analize.diff(previous, current);

        assertThat(result.added, is(1));
        assertThat(result.changed, is(1));
        assertThat(result.deleted, is(1));
    }
}