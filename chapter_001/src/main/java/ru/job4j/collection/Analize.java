package ru.job4j.collection;

import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        var rsl = new Info();

        Map<Integer, String> prevMap = previous.stream().collect(
                Collectors.toMap(user -> user.id, user -> user.name)
        );

        for (User user: current) {
            if (!prevMap.containsKey(user.id)) {
                rsl.added++;
            } else {
                if (!Objects.equals(prevMap.get(user.id), user.name)) {
                    rsl.changed++;
                }
            }
            prevMap.remove(user.id);
        }
        rsl.deleted = prevMap.size();

        return rsl;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
