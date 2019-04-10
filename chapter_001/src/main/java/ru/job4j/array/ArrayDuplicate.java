package ru.job4j.array;

import java.util.Arrays;

/**
 * удаление дубликатов массива
 * @author ivannikov
 * @version $Id$
 * @since 0.1
 */

public class ArrayDuplicate {

    /**
     *
     * @param array входящий массив
     * @return  Метод должен переместить все дубликаты строк из массива в его конец,
     * обрезать их и вернуть массив уникальных строк
     */
    public String[] remove(String[] array) {
        String tmp;
        int lastIndex = array.length - 1;
        for (int i = 1; i <= lastIndex; i++) {
            for (int j = i; j <= lastIndex; j++) {
                if  (array[i - 1].equals(array[j])) {         //нашли дубликат
                    while (array[j].equals(array[lastIndex])) { //если и пока последний эл тоже равен дубликату
                        lastIndex--;                         //смещаемся от последнего ниже
                    }
                    tmp = array[j];
                    array[j] = array[lastIndex];
                    array[lastIndex--] = tmp;
                }
            }
        }
        return Arrays.copyOf(array, lastIndex + 1);
    }

}
