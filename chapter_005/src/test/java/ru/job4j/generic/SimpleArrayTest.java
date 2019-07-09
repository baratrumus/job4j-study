package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

public class SimpleArrayTest {

    private Iterator<Integer> it;
    SimpleArray<Integer> simple;

    @Before
    public void setUp() {
        simple = new SimpleArray<Integer>(3);
    }

    @Test
    public void whenAddTwoThenGetTwo() {
        simple.add(2);
        int result = simple.get(0);
        assertThat(result, is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddFourItsOutOfBound() {
        simple.add(2);
        simple.add(2);
        simple.add(3);
        simple.add(4);
    }

    @Test
    public void whenRemoveTwoThenRemoved() {
        simple.add(2);
        simple.add(3);
        simple.add(4);
        simple.remove(1);
        int result1 = simple.get(1);
        Integer result2 = simple.get(2);
        assertThat(result1, is(4));
        assertNull(result2);
    }

    @Test
    public void whenSetFourItsSet() {
        simple.add(2);
        simple.set(0, 4);
        int result = simple.get(0);
        assertThat(result, is(4));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnIterations() {
        simple.add(2);
        simple.add(3);
        simple.add(4);
        Iterator<Integer> it = simple.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}
