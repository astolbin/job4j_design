package ru.job4j.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class User {
    String name;
    int children;
    Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        var a = new User("user 1", 2, new GregorianCalendar(1985, Calendar.JUNE, 20));
        var b = new User("user 1", 2, new GregorianCalendar(1985, Calendar.JUNE, 20));

        var map = new HashMap<User, Object>();
        map.put(a, 1);
        map.put(b, 2);

        for (User user : map.keySet()) {
            System.out.println(map.get(user));
        }
    }
}
