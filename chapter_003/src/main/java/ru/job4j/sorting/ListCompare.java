package ru.job4j.sorting;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {
    @Override
    /**
     * сравниваем элементы двух списков, находящихся на одних и тех же позициях (по одним и тем же индексом).
     * Сравнение в лексикографическом порядке. a<b.
     * Размер цикла по меньшему массиву.
     * Второе условие проверяет короткая строка меньше длинной.
     */
    public int compare(String left, String right) {
        char[] arLeft = left.toCharArray();
        char[] arRight = right.toCharArray();
        int res = 0;
        int charRes = 0;
        int size = (arLeft.length > arRight.length) ? arRight.length : arLeft.length;
        for (int i = 0; i < size; i++) {
            charRes = Character.compare(arLeft[i], arRight[i]);
            if (charRes != 0) {
                res = charRes;
                break;
            }
        }
        if (res == 0)  {
            res = Integer.compare(arLeft.length, arRight.length);
        }
    return res;
    }
}
