package ru.job4j.set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {

    private SimpleSet<Integer> set;

    @Before
    public void beforeTest() {
        set = new SimpleSet<>(10);

    }

    @Test
    public void whenAddThreeThenAdded() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(2);
        assertThat(set.get(0), is(1));
        assertThat(set.getSize(), is(3));
    }

    @Test
    public void whenAddNullThenAdded() {
        set.add(0);
        set.add(null);
        set.add(null);
        set.add(2);
        set.add(2);
        set.add(null);
        set.add(2);
        assertThat(set.get(0), is(0));
        Assert.assertNull(set.get(1));
        assertThat(set.get(2), is(2));
        assertThat(set.getSize(), is(3));
    }
}
