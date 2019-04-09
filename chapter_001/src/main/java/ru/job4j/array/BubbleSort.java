package ru.job4j.array;


/**
 * Сортировка пузырьком
 * @author ivannikov
 * @version $Id$
 * @since 0.1
 */
public class BubbleSort {
    /**
     *
     * @param array входящий массив
     * @return отсортированный массив
     */
    public int[] sort(int[] array) {
        int tmp;
        for (int i = array.length; i > 1; i--) {
            for (int j = 1; j < i; j++) {
                if (array[j - 1] > array[j]) {
                    tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
        return array;
    }


}
