package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {
    private SimpleStack<Integer> stack = new SimpleStack<>();

    @Before
    public void beforeTest() {
        stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    public void whenPopThenPopped() {
        assertThat(stack.poll(), is(3));
        assertThat(stack.size, is(2));
        assertThat(stack.poll(), is(2));
        assertThat(stack.size, is(1));
        assertThat(stack.poll(), is(1));
        assertThat(stack.size, is(0));
    }

}
