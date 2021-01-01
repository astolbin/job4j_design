package ru.job4j.collection.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mail {
    private final HashMap<String, String> emailNameMap = new HashMap<>();
    private final HashMap<String, String> nameNameMap = new HashMap<>();

    public List<User> merge(List<User> userList) {
        getEmailMaps(userList);
        return mergeMaps();
    }

    private List<User> mergeMaps() {
        var mergedMap = new HashMap<String, User>();
        var rslList = new ArrayList<User>();

        for (String email : emailNameMap.keySet()) {
            var name = emailNameMap.get(email);
            if (nameNameMap.containsKey(name)) {
                name = nameNameMap.get(name);
            }
            User user;
            if (mergedMap.containsKey(name)) {
                user = mergedMap.get(name);
            } else {
                user = new User(name);
                mergedMap.put(name, user);
                rslList.add(user);
            }
            user.addEmail(email);
        }

        return rslList;
    }

    /**
     * метод строит карту вида email -> name для получения списка уникальных имейлов,
     * и так же строит карту вида name -> name для привязки повторяющихся имейлов к тем
     * пользователям, у которых имейл впервые был обнаружен
     */
    private void getEmailMaps(List<User> userList) {
        for (User user : userList) {
            for (String email : user.getEmails()) {
                if (emailNameMap.containsKey(email)) {
                    var firstUserName = emailNameMap.get(email);
                    if (nameNameMap.containsKey(firstUserName)) {
                        firstUserName = nameNameMap.get(firstUserName) ;
                    }
                    nameNameMap.put(user.getName(), firstUserName);
                } else {
                    emailNameMap.put(email, user.getName());
                }
            }
        }
    }
}
