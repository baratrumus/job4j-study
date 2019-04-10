package ru.job4j.array;

public class MatrixCheck {
    private final boolean[][] matrix;
    private final int size;

    public MatrixCheck(boolean[][] sourceMatrix) {
        matrix = sourceMatrix;
        size = sourceMatrix.length;
    }

    /**
     *
     * @return true если все элементы по диагоналям равны true или false
     * Если диагонали не содержат общих ячеек, тогда диагонали могут быть разными по значению,
     *  например, одна диагональ - true, вторая-  false.
     */
    public boolean mono() {
        boolean leftToTopRight = true;
        boolean leftToBottomRight = true;
        for (int i = 1; i < size; i++) {
            if (matrix[size - i][i - 1] != matrix[size - i - 1][i]) {
                leftToTopRight = false;
            }
            if (matrix[i - 1][i - 1] != matrix[i][i]) {
                leftToBottomRight = false;
            }
        }
        return (size % 2 == 0) ? (leftToTopRight || leftToBottomRight) : (leftToTopRight && leftToBottomRight);
    }

}
