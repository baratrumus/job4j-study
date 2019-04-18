package ru.job4j.array;

/**
 * Даны два отсортированных массив. Надо без сортировки их объединить в третий массив
 * @author ivannikov
 * @version $Id$
 * @since 0.1
 */

public class ArrayMerge {

    /**
     * последовательно сравниваем элементы из левого и правого массива.
     * Если элемент меньше, то его помещаем в новый массив, при этом сдвигаем указатель
     * Во втором и третьем while обрабатываем ситуации когда один массив
     * закончился а второй нет
     * @param first  первый входной массив
     * @param second второй входной массив
     * @return результирующий массив
     */

    public int[] arrMerge(int[] first, int[] second) {
        int[] rezArr = new int[first.length + second.length];
        int rez = 0;
        int fst = 0;
        int sec = 0;
        while ((fst < first.length)  && (sec < second.length)) {
            rezArr[rez++] = (first[fst] < second[sec]) ? first[fst++] : second[sec++];
        }
        while (fst < first.length) {
            rezArr[rez++] = first[fst++];
        }
        while (sec < second.length) {
            rezArr[rez++] = second[sec++];
        }
        return rezArr;
    }
}
