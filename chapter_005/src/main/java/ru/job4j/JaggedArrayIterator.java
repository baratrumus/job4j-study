package ru.job4j;

import java.util.Iterator;

public class JaggedArrayIterator implements Iterator {
    private final int[][] array;
    private int indexX = 0;
    private int indexY = 0;
    private int currentIndex = 0;
    private final int arrLength;


    JaggedArrayIterator(int[][] array) {
        this.array = array;
        arrLength = getLength();
    }

    @Override
    public boolean hasNext() {
        return currentIndex < arrLength;
    }

    private int getLength() {
        int length = 0;
        for (int[] row: array) {
            length += row.length;
        }
        return length;
    }


    @Override
    public Object next() {
        Object ret = array[indexX][indexY];
        if (array[indexX].length > indexY + 1) {
            indexY++;
        } else {
            indexX++;
            indexY = 0;
        }
        currentIndex++;
        return ret;
    }


}
