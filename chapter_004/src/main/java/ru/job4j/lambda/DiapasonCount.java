package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Реализовать метод подсчета функции в диапазоне.
 * Реализации функций в тестах.
 *     - линейная.
 *     - квадратичная.
 *     - логарифмическая.
 */
public class DiapasonCount {

    public static List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> dList = new ArrayList<>();
        for (int i = start; i < end; i++) {
            dList.add(func.apply(Double.valueOf(i)));
        }
        return dList;
    }
}
