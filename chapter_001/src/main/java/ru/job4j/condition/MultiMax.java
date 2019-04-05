package ru.job4j.condition;

/**
 * Class Класс нахождение максимального из трёх чисел
 * @author ivannikov
 * @since 05.04.2019
 * @version 1
 */

public class MultiMax {
    public int max(int first, int second, int third) {
        int result = first > second ? first : second;
        result = result > third ? result : third;
        return result;
    }
}