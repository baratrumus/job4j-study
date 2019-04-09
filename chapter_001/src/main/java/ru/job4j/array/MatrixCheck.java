package ru.job4j.array;

public class MatrixCheck {
    private final boolean[][] matrix;
    private final int size;

    public MatrixCheck(boolean[][] sourceMatrix) {
        this.matrix = sourceMatrix;
        size = sourceMatrix.length;
    }

    /**
     * @return  true если все элементы диагонали лево верх - право низ  имеют одинаковое значение
     */
    private boolean leftToTopRight() {
        boolean rst = true;
        for (int i = 1; i < size; i++) {
            if (matrix[size - i][i - 1] != matrix[size - i - 1][i]) {
                rst = false;
            }
        }
        return rst;
    }

    /**
     * @return  true если все элементы диагонали слева снизу - право верх  имеют одинаковое значение
     */
    private boolean leftToBottomRight() {
        boolean rst = true;
        for (int i = 1; i < size; i++) {
            if (matrix[i - 1][i - 1] != matrix[i][i]) {
                rst = false;
            }
        }
        return rst;
    }

    /**
     *
     * @return true если все элементы по диагоналям равны true или false
     * Если диагонали не содержат общих ячеек, тогда диагонали могут быть разными по значению,
     *  например, одна диагональ - true, вторая-  false.
     */
    public boolean mono() {
        return (size % 2 == 0) ? (leftToTopRight() || leftToBottomRight()) : (leftToTopRight() && leftToBottomRight());

    }


}
