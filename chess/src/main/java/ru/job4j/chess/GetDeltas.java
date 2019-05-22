package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;

public class GetDeltas {

    /**
     * Назначить приращение x y для диагональных ходов
     * @param source
     * @param dest
     * @return
     */
    public int[] getDiagonalDeltas(Cell source, Cell dest) {
        int deltaX = 0;
        int deltaY = 0;
        int deltaMove = Math.abs(dest.x - source.x);
        int[] ret = new int[3];
        if ((source.x < dest.x) && (source.y > dest.y)) {                    //ход вправо вверх
            deltaX = 1;
            deltaY = -1;
        } else if ((source.x < dest.x) && (source.y < dest.y)) {              //ход вправо вниз
            deltaX = 1;
            deltaY = 1;
        } else if ((source.x > dest.x) && (source.y > dest.y)) {              //ход влево вверх
            deltaX = -1;
            deltaY = -1;
        } else if ((source.x > dest.x) && (source.y < dest.y)) {              //ход влево вниз
            deltaX = -1;
            deltaY = 1;
        }
        ret[0] = deltaX;
        ret[1] = deltaY;
        ret[2] = deltaMove;
        return ret;
    }

    /**
     * Назначить приращение x y для линейных ходов
     * @param source
     * @param dest
     * @return
     */
    public int[] getHorizontalDeltas(Cell source, Cell dest) {
        int deltaX = 0;
        int deltaY = 0;
        int deltaMove = Math.abs(dest.x - source.x);
        int[] ret = new int[3];
        if (source.x == dest.x) {
            if (source.y < dest.y) {                    //ход вертикально вниз
                deltaY = 1;
                deltaMove = dest.y - source.y;
            } else if (source.y > dest.y) {              //ход вертикально вверх
                deltaY = -1;
                deltaMove = source.y - dest.y;
            }
        } else if (source.y == dest.y) {
            if (source.x < dest.x) {                     //ход вправо
                deltaX = 1;
                deltaMove = dest.x - source.x;
            } else if (source.x > dest.x) {              //ход влево
                deltaX = -1;
                deltaMove =  source.x - dest.x;
            }
        }
        ret[0] = deltaX;
        ret[1] = deltaY;
        ret[2] = deltaMove;
        return ret;
    }
}
