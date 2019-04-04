package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    @Test
    public void whenAddOnePlusOneThenTwo()  {
        Calculator calc = new Calculator();
        double result = calc.add(1D, 1D);
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenSubtractThreeMinusOneThenTwo()  {
        Calculator calc = new Calculator();
        double result = calc.subtract(3D, 1D);
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenThreeDividedbyTwoThenOneAndFive()  {
        Calculator calc = new Calculator();
        double result = calc.div(3D, 2D);
        double expected = 1.5D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMultiplyThreeAndTwoThenSix()  {
        Calculator calc = new Calculator();
        double result = calc.multiple(3D, 2D);
        double expected = 6D;
        assertThat(result, is(expected));
    }


}

