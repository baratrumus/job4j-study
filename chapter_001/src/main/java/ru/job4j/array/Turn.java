package ru.job4j.array;

/**
 * Class Класс переворота массива
 * @author ivannikov
 * @version $Id$
 * @since 0.1
 */
public class Turn {
    /**
     *
     * @param array входящий массив
     * @return перевернутый массив
     */
    public int[] back(int[] array) {
        int tmp;
        for (int i = 0; i < array.length / 2; i++) {
            tmp = array[array.length - i - 1];
            array[array.length - i - 1] = array[i];
            array[i] = tmp;
        }
        return array;
    }
}

