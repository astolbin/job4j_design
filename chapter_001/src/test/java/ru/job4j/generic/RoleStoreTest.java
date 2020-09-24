package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class RoleStoreTest {

    private RoleStore store;

    @Before
    public void setup() {
        store = new RoleStore();
        store.add(new Role("Role1"));
        store.add(new Role("Role2"));
        store.add(new Role("Role3"));
    }

    @Test
    public void whenFindByIdSuccess() {
        Role result = store.findById("Role1");
        assertThat(result.equals(new Role("Role1")), is(true));
    }

    @Test
    public void whenFindByIdNotFound() {
        assertNull(store.findById("Role4"));
    }

    @Test
    public void whenReplaceSuccess() {
        assertThat(store.replace("Role1", new Role("Role5")), is(true));
    }

    @Test
    public void whenReplaceNotFound() {
        assertThat(store.replace("Role4", new Role("Role5")), is(false));
    }

    @Test
    public void whenDeleteSuccess() {
        assertThat(store.delete("Role1"), is(true));
        assertNull(store.findById("Role1"));
    }

    @Test
    public void whenDeleteNotFound() {
        assertThat(store.delete("Role4"), is(false));
    }
}