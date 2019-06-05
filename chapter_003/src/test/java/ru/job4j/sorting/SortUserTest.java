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
                new User("Petr", 45),
                new User("Zuma", 53),
                new User("Oleg", 42)));

        TreeSet<User> result = SortUser.sort(userList);
        Iterator<User> iter = result.iterator();
        assertThat(result.first().getName(), is("Oleg"));
        User u = iter.next();
        assertThat(result.higher(u).getName(), is("Petr"));
        assertThat(result.last().getName(), is("Zuma"));
    }
}
