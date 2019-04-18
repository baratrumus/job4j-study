package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;


public class TriangleTest {
    @Test
    public void whenAreaSetThreePointsThenTriangleArea4And4() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 4);
        Point p3 = new Point(4, 1);
        Triangle triangle = new Triangle(p1, p2, p3);
        double result = triangle.area();
        double expected = 4.4D;
        assertThat(result, closeTo(expected, 0.1));
    }

    @Test
    public void whenAreaSetThreePointsThenTriangleArea7And5() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 6);
        Point p3 = new Point(4, 2);
        Triangle triangle = new Triangle(p1, p2, p3);
        double result = triangle.area();
        double expected = 7.4D;
        System.out.println(String.format("Result is %s", result));
        assertThat(result, closeTo(expected, 0.2));
    }
}