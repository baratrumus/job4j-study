package ru.job4j.sorting;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.*;

public class SortUserTest {
    @Test
    public void whenListSortToTree() {
        List<User> userList = new ArrayList<User>();
        userList.addAll(Arrays.asList(
                new User("Petr", 25),
                new User("Auma", 25),
                new User("Zuma", 32),
                new User("Oleg", 12)));

        TreeSet<User> result = SortUser.sort(userList);
        Iterator<User> iter = result.iterator();
        assertThat(result.first().getName(), is("Oleg"));
        User u = iter.next();
        assertThat(result.higher(u).getName(), is("Auma"));
        iter.next();
        assertThat(iter.next().getName(), is("Petr"));
        assertThat(result.last().getAge(), is(Integer.valueOf(32)));
    }
}
