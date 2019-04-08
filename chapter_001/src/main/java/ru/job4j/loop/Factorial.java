package ru.job4j.loop;

/**
 * Class Класс вычисления факториала
 * @author ivannikov
 * @since 08.04.2019
 * @version 1
 */

public class Factorial {
    public int calc(int n) {
        return ((n == 1) || (n == 0)) ? 1 : calc(n - 1) * n;
    }
}