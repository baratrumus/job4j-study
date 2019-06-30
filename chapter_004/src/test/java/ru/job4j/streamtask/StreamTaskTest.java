package ru.job4j.streamtask;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StreamTaskTest {
    @Test
    public void whenGetProfilesTakeAdress() {
        Integer[] arr = {1, 2, 3, 4};

        StreamTask st = new StreamTask();
        Integer result = st.streamTask(arr);
        assertThat(result, is(20));
    }
}