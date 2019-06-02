package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Конвертация двумерного массива в ArrayList через foreach
 */
public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] row: array) {
            for (int cellInRow: row) {
                list.add(cellInRow);
            }
        }
        return list;
    }
}
