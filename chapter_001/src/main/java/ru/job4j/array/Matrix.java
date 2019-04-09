package ru.job4j.array;

/**
 * Class Класс заполнения матрицы таблицей умножения.
 * @author ivannikov
 * @version $Id$
 * @since 0.1
 */

public class Matrix {
    /**
     *
     * @param size размер матрицы
     * @return матрица таблицы умножения
     */
    int[][] multiple(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
            }
        }
        return matrix;
    }
}
