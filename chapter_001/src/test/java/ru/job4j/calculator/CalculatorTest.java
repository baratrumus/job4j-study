package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    @Test
    public void whenAddOnePlusOneThenTwo()  {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getRes();
        double expected =  2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenSubtractThreeMinusOneThenTwo()  {
        Calculator calc = new Calculator();
        calc.subtract(3D, 1D);
        double result = calc.getRes();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenThreeDivByTwoThenOneAndFive()  {
        Calculator calc = new Calculator();
        calc.div(3D, 2D);
        double result = calc.getRes();
        double expected = 1.5D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMultiplyThreeAndTwoThenSix()  {
        Calculator calc = new Calculator();
        calc.multiple(3D, 2D);
        double result = calc.getRes();
        double expected = 6D;
        assertThat(result, is(expected));
    }
}

