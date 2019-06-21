package ru.job4j.lambda;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

public class DiapasonCountTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = DiapasonCount.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogFunctionThenLogResults() {
        List<Double> result = DiapasonCount.diapason(5, 8, x -> Math.log(x) + 1);
        List<Double> expected = Arrays.asList(2.6D, 2.8D, 2.94D);
        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i), closeTo(expected.get(i), 0.01));
        }
    }

    @Test
    public void whenSquareFunctionThenSquareResults() {
        List<Double> result = DiapasonCount.diapason(5, 8, x -> x * x);
        List<Double> expected = Arrays.asList(25D, 36D, 49D);
        assertThat(result, is(expected));
    }
}
