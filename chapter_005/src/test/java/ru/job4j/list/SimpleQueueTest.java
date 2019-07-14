package ru.job4j.list;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    SimpleQueue<Integer> queue = new SimpleQueue<>();

    @Test
    public void whenPushThreeThenPollIt() {
        queue.push(1);
        queue.push(2);
        assertThat(queue.poll(), is(1));
        queue.push(3);
        assertThat(queue.poll(), is(2));
        queue.push(4);
        assertThat(queue.poll(), is(3));
        assertThat(queue.poll(), is(4));
        assertNull(queue.poll());
    }

}
