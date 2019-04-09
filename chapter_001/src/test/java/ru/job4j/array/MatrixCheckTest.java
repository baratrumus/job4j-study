package ru.job4j.array;


import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixCheckTest {
    @Test
    public void when3AndDataMonoByTrueThenTrue() {
        boolean[][] input = new boolean[][] {
                {true, true, true},
                {false, true, true},
                {true, false, true}
        };
        MatrixCheck check = new MatrixCheck(input);
        boolean result = check.mono();
        assertThat(result, is(true));
    }

    @Test
    public void when4AndDataNotMonoByTrueThenFalse() {
        boolean[][] input = new boolean[][] {
                {false, true, false, true},
                {false, false, true, false},
                {true, true, false, true},
                {true, false, true, false}
        };
        MatrixCheck check = new MatrixCheck(input);
        boolean result = check.mono();
        assertThat(result, is(true));
    }
}