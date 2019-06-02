package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {

    /**
     *метод toArray должен равномерно разбить лист на количество строк двумерного массива.
     * В методе toArray должна быть проверка - если количество элементов не кратно
     * количеству строк - оставшиеся значения в массиве заполнять нулями.
     * Например в результате конвертации листа со значениями (1,2,3,4,5,6,7)
     * с разбиением на 3 строки должен получиться двумерный массив {{1, 2, 3} {4, 5, 6} {7, 0 ,0}}
     * @param list
     * @param rowsAmount количество рядов
     * @return
     */
    public int[][] toArray(List<Integer> list, int rowsAmount) {
        int colsAmount = list.size() / rowsAmount;
        boolean isFull = (colsAmount * rowsAmount == list.size());
        if (!isFull) {
            colsAmount++;
        }
        int[][] array = new int[rowsAmount][colsAmount];
        int row = 0;
        int col = -1;
        for (Integer elem: list) {
            if (col < colsAmount - 1) {
                col++;
            } else {
                col = 0;
                row++;
            }
            array[row][col] = elem;
        }
        if (!isFull) {
            int zeroAmount = (colsAmount * rowsAmount) - list.size();
            while (col++ < zeroAmount) {
                array[row][col] = 0;
            }
        }
        return array;
    }
}
