package ru.job4j.calculator;

/**
 * Class Класс для реализации методов калькулятора
 * @author ivannikov
 * @since 05.04.2019
 * @version 1
 */

public class Calculator {
    /**
     * @param first  first operand
     * @param second second operand
     * @return  sum  of first and second
     */
    public double add(double first, double second) {
        return first + second;
    }

    /**
     * @param first  first operand
     * @param second second operand
     * @return  subtract  of first and second
     */
    public double subtract(double first, double second) {
        return first - second;
    }

    /**
     * @param first  first operand
     * @param second second operand
     * @return   div of first and second
     */
    public double div(double first, double second) {
        return first / second;
    }

    /**
     * @param first   first  operand
     * @param second second operand
     * @return  multiple  of first and second
     */
    public double multiple(double first, double second) {
        return first * second;
    }
}
