package ru.job4j.gc;

public class User {
    private int id;
    private final String name;

    public User(String name) {
        this.name = name;
    }

    public User(int id, String name) {
        this(name);
        this.id = id;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", id, name);
    }
}
