package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DynamicArrayTest {
    private DynamicArray<Integer> list;

    @Before
    public void beforeTest() {
        list = new DynamicArray<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeThenAdded() {
        assertThat(list.get(0), is(1));
        assertThat(list.getSize(), is(3));
    }


    @Test(expected = ConcurrentModificationException.class)
    public void failFastThrowConcurrentModificationException() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
        list.add(4);
        it.next();
    }

}
