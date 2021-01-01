package ru.job4j.collection.mail;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MailTest {
    @Test
    public void whenMerge() {
        var user1 = new User("usr1", List.of("tst1@mail.ru", "tst2@mail.ru"));
        var user2 = new User("usr2", List.of("tst3@mail.ru", "tst4@mail.ru"));
        var user3 = new User("usr3", List.of("tst5@mail.ru", "tst1@mail.ru"));
        var user4 = new User("usr4", List.of("tst6@mail.ru", "tst10@mail.ru", "tst5@mail.ru"));
        var user5 = new User("usr5", List.of("tst4@mail.ru", "tst7@mail.ru"));
        var user6 = new User("usr6", List.of("tst7@mail.ru", "tst8@mail.ru"));
        var user7 = new User("usr7", List.of("tst6@mail.ru", "tst9@mail.ru"));
        var user8 = new User("usr8", List.of("tst10@mail.ru", "tst11@mail.ru"));

        var userList = List.of(user1, user2, user3, user4, user5, user6, user7, user8);
        var rsl = new Mail().merge(userList);

        var usr1 = rsl.indexOf(new User("usr1"));
        var usr2 = rsl.indexOf(new User("usr2"));

        var emailList1 = List.of("tst1@mail.ru", "tst2@mail.ru", "tst5@mail.ru", "tst6@mail.ru", "tst9@mail.ru", "tst10@mail.ru", "tst11@mail.ru");
        var emailList2 = List.of("tst3@mail.ru", "tst4@mail.ru", "tst7@mail.ru", "tst8@mail.ru");

        assertTrue(rsl.get(usr1).getEmails().containsAll(emailList1));
        assertTrue(rsl.get(usr2).getEmails().containsAll(emailList2));
        assertThat(rsl.size(), is(2));
    }
}