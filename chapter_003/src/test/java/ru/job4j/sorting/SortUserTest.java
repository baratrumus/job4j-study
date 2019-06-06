package ru.job4j.sorting;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.*;

public class SortUserTest {
    List<User> userList = new ArrayList<>();

    @Test
    public void whenListSortToTree() {
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
        assertThat(result.last().getAge(), is(32));
    }

    @Test
    public void sortNameLengthTest() {
        userList.addAll(Arrays.asList(
                new User("Petr", 25),
                new User("Aumata", 25),
                new User("Zuman", 32),
                new User("Ola", 12)));

        List<User> result = SortUser.sortNameLength(userList);
        Iterator<User> iter = result.iterator();
        assertThat(iter.next().getName(), is("Ola"));
        assertThat(iter.next().getName(), is("Petr"));
        assertThat(iter.next().getName(), is("Zuman"));
        assertThat(iter.next().getName(), is("Aumata"));
    }

    @Test
    public void sortByAllFieldsTest() {
        userList.addAll(Arrays.asList(
                new User("Zuma", 22),
                new User("Aumata", 25),
                new User("Zuma", 32),
                new User("Oleg", 12)));

        List<User> result = SortUser.sortByAllFields(userList);
        Iterator<User> iter = result.iterator();
        assertThat(iter.next().getName(), is("Aumata"));
        assertThat(iter.next().getName(), is("Oleg"));
        assertThat(iter.next().getAge(), is(22));
        assertThat(iter.next().getAge(), is(32));
    }
}
