package ru.job4j.condition;

/**
 * Class Класс для вычисления максимума 2-х чисел
 * @author ivannikov
 * @since 05.04.2019
 * @version 1
 */

public class Max {
    /**
     *
     * @param left первый операнд
     * @param right второй операнд
     * @return максимальное из них
     */
    public int max(int left, int right) {
        return left < right ? right : left;
    }
}
