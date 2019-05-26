package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;

public class GetLineType {

    public boolean isDiagonal(Cell source, Cell dest) {
        boolean ret = false;
        int deltaX = 0;
        int deltaY = 0;
        int tmpX = source.x;
        int tmpY = source.y;
        if (source.x < dest.x) {
            deltaX = 1;
        } else if (source.x > dest.x) {
            deltaX = -1;
        } else {
            return ret;
        }
        if (source.y < dest.y) {
            deltaY = 1;
        } else if (source.y > dest.y) {
            deltaY = -1;
        } else {
            return ret;
        }
        for (int i = 0; i < 7; i++) {
            tmpX += deltaX;
            tmpY += deltaY;
            if (tmpX == dest.x && tmpY == dest.y) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public boolean isLine(Cell source, Cell dest) {
        boolean ret = false;
        if (source.x == dest.x || source.y == dest.y) {
            ret = true;
        }
        return ret;
    }
}
