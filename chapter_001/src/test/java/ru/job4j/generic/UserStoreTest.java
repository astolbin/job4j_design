package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class UserStoreTest {

    private UserStore store;

    @Before
    public void setup() {
        store = new UserStore();
        store.add(new User("User1"));
        store.add(new User("User2"));
        store.add(new User("User3"));
    }

    @Test
    public void whenFindByIdSuccess() {
        User result = store.findById("User1");
        assertThat(result.equals(new User("User1")), is(true));
    }

    @Test
    public void whenFindByIdNotFound() {
        assertNull(store.findById("User4"));
    }

    @Test
    public void whenReplaceSuccess() {
        assertThat(store.replace("User1", new User("User3")), is(true));
    }

    @Test
    public void whenReplaceNotFound() {
        assertThat(store.replace("User4", new User("User2")), is(false));
    }

    @Test
    public void whenDeleteSuccess() {
        assertThat(store.delete("User1"), is(true));
        assertNull(store.findById("User1"));
    }

    @Test
    public void whenDeleteNotFound() {
        assertThat(store.delete("User4"), is(false));
    }
}
