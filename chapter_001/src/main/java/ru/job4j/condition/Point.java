package ru.job4j.condition;


/**
 * Class Класс для вычисления дистанций
 * @author ivannikov
 * @since 05.04.2019
 * @version 1
 */

public class Point {
    /**
     * @param x1 x первой точки
     * @param y1 y первой точки
     * @param x2 x второй точки
     * @param y2 y второй точки
     * @return расстояние между точками
     */
    public double distance(int x1, int y1, int x2, int y2) {

        double first = Math.pow(x2 - x1, 2);
        double second = Math.pow(y2 - y1, 2);
        return Math.sqrt(first + second);
    }
}

