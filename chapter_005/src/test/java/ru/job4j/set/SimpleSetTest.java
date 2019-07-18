package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {

    private SimpleSet<Integer> set;

    @Before
    public void beforeTest() {
        set = new SimpleSet<>(3);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(2);
    }

    @Test
    public void whenAddThreeThenAdded() {
        assertThat(set.get(0), is(1));
        assertThat(set.getSize(), is(3));
    }

}
