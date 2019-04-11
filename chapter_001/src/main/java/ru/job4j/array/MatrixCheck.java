package ru.job4j.array;

public class MatrixCheck {
    private final boolean[][] matrix;
    private final int size;

    public MatrixCheck(boolean[][] sourceMatrix) {
        matrix = sourceMatrix;
        size = sourceMatrix.length;
    }

    /**
     * @param "exactValueD1" значение ячеек диагонали лево верх - право низ
     * @return true если все элементы по диагоналям равны true или false
     * Если диагонали не содержат общих ячеек, тогда диагонали могут быть разными по значению,
     * например, одна диагональ - true, вторая-  false.
     */
    public boolean mono() {
        final boolean exactValueD1 = matrix[0][0];
        final boolean exactValueD2 = matrix[0][size - 1];
        boolean diagonalAssembled = true;
        for (int i = 1; i < size; i++) {
            if (matrix[size - i][i - 1] != matrix[size - i - 1][i]) { //лево низ - право верх
                diagonalAssembled = false;
                break;
            }
            if (matrix[i - 1][i - 1] != matrix[i][i]) {
                diagonalAssembled = false;
                break;
            }
        }
        //проверка нечетного размера матрицы на несовпадение значений ячеек диагоналей
        if (size % 2 != 0) {
            if (exactValueD1 != exactValueD2) {
                diagonalAssembled = false;
            }
        }

        return diagonalAssembled;

    }
}

