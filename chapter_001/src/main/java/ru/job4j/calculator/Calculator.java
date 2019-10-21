package ru.job4j.calculator;

/**
 * Class Класс для реализации методов калькулятора
 * @author ivannikov
 * @since 05.04.2019
 * @version 1
 */

public class Calculator {

    private static double res = 0;
    /**
     * @param first  first operand
     * @param second second operand
     * @return  sum  of first and second
     */
    public void add(double first, double second) {
        res = first + second;
    }

    /**
     * @param first  first operand
     * @param second second operand
     * @return  subtract  of first and second
     */
    public void subtract(double first, double second) {
        res = first - second;
    }

    /**
     * @param first  first operand
     * @param second second operand
     * @return   div of first and second
     */
    public void div(double first, double second) {
        res = first / second;
    }

    /**
     * @param first   first  operand
     * @param second second operand
     * @return  multiple  of first and second
     */
    public void multiple(double first, double second) {
        res = first * second;
    }

    public double getRes() {
        return res;
    }



}
