package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DynamicLinkedListTest {
    private DynamicLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new DynamicLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeThenAdded() {
        assertThat(list.getData(list.get(0)), is(1));
        assertThat(list.getData(list.get(1)), is(2));
        assertThat(list.getData(list.get(2)), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFourThenIndexOutOfBoundsException() {
        assertThat(list.getData(list.get(4)), is(1));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void failFastThrowConcurrentModificationException() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
        list.add(4);
        it.next();
    }

    @Test
    public void whenNextItsWorking() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenRemoveLastItsWorking() {
        list.removeLast();
        assertThat(list.last.data, is(2));
        assertThat(list.size, is(2));
    }

}
