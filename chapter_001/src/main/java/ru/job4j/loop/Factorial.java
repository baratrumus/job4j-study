package ru.job4j.loop;

/**
 * Class Класс вычисления факториала
 * @author ivannikov
 * @since 08.04.2019
 * @version 1
 */

public class Factorial {

  // факториал через рекурсию
   // public int calc(int n) {
   //     return ((n == 1) || (n == 0)) ? 1 : calc(n - 1) * n; }
    /**
     * факториал через цикл
     * @param n размер ряда
     * @return факториал n
     */

    public int calc(int n) {
        int res = 1;
        if (n == 0) {
            return res;
        }
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }
}