package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Создать итератор возвращающий только четные цифры.
 * Итератор должен принимать список произвольных чисел.
 */

public class EvenIterator implements Iterator {
    private final int[] array;
    private int index = 0;

    EvenIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
       return findNext();
    }


    @Override
    public Object next() {
        if (!findNext()) {
            throw new NoSuchElementException();
        }
        return array[index++];
    }


    private boolean findNext() {
        boolean res = false;
        for (; index < array.length; index++) {
            if (((array[index] % 2) == 0) && (array[index] != 0)) {
                res = true;
                break;
            }
        }
        return res;
    }
}