package ru.job4j.array;

/**
 * Class Класс должен проверить, что все элементы в массиве являются true или false.
 * @author ivannikov
 * @version $Id$
 * @since 0.1
 */
public class Check {

    /**
     * 372f62f
     * @param data  входящий массив
     * @return true or false
     */

    public boolean mono(boolean[] data) {
        boolean result = false;
        boolean tmp = data[0];
        for (int i = 1; i < data.length; i++) {
            if (tmp == data[i]) {
                result = true;
            } else {
                return false;
            }
        }
        return result;
    }
}
