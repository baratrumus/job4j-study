package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
    @Test
    public void whenMax1To2Then2() {
        Max max = new Max();
        int result = max.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenMax5To2Then5() {
        Max max = new Max();
        int result = max.max(5, 2);
        assertThat(result, is(5));
    }

    @Test
    public void whenMax6To6Then2() {
        Max max = new Max();
        int result = max.max(6, 6);
        assertThat(result, is(6));
    }
}
