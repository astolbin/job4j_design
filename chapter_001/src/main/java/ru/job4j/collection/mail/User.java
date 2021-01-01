package ru.job4j.collection.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private final String name;
    private final List<String> emails;

    public User(String name) {
        this.name = name;
        this.emails = new ArrayList<>();
    }

    public User(String name, List<String> emails) {
        this.name = name;
        this.emails = emails;
    }

    public void addEmail(String email) {
        emails.add(email);
    }

    public String getName() {
        return name;
    }

    public List<String> getEmails() {
        return emails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
