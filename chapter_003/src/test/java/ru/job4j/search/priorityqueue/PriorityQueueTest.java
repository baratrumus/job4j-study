package ru.job4j.search.priorityqueue;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 3));
        queue.put(new Task("middle", 2));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("verylow", 5));
        queue.put(new Task("middle2", 2));

        assertThat(queue.take(0).getDesc(), is("urgent"));
        assertThat(queue.take(1).getDesc(), is("middle"));
        assertThat(queue.take(2).getDesc(), is("middle2"));
        assertThat(queue.take(3).getDesc(), is("low"));
        assertThat(queue.take(4).getDesc(), is("verylow"));
    }
}
