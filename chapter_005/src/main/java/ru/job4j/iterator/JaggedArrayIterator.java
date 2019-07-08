package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * создать итератор для двухмерного массива.
 */

public class JaggedArrayIterator implements Iterator {
    private final int[][] array;
    private int rowIndex = 0;
    private int colIndex = 0;

    JaggedArrayIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return ((rowIndex < array.length) && (colIndex < array[rowIndex].length));
    }


    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object ret = array[rowIndex][colIndex];
        if (colIndex + 1 < array[rowIndex].length) {
            colIndex++;
        } else {
            rowIndex++;
            colIndex = 0;
        }
        return ret;
    }
}
