package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;

public class GetLineType {

    public boolean isDiagonal(Cell source, Cell dest) {
        boolean  ret = false;
        int deltaX = Math.abs(source.x - dest.x);
        int deltaY = Math.abs(source.y - dest.y);
        if (deltaX == deltaY) {
            ret = true;
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
