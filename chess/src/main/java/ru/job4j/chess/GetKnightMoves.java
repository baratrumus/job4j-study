package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.Logic;
import java.util.Arrays;

public class GetKnightMoves {

    Logic logic = new Logic();
    /**
     *
     * @param s
     * @return все возможные ходы коня из текущей точки
     */
    public Cell[] getKnightMoves(Cell s) {
        Cell[] allowedMoves = new Cell[8];
        int i = 0;
        Cell cell;
        cell = logic.findCellByXY(s.x + 1, s.y - 2);
        if (cell != null) {
            allowedMoves[i] = cell;
            i++;
        }
        cell = logic.findCellByXY(s.x + 2, s.y - 1);
        if (cell != null) {
            allowedMoves[i] = cell;
            i++;
        }
        cell = logic.findCellByXY(s.x + 2, s.y + 1);
        if (cell != null) {
            allowedMoves[i] = cell;
            i++;
        }
        cell = logic.findCellByXY(s.x + 1, s.y + 2);
        if (cell != null) {
            allowedMoves[i] = cell;
            i++;
        }
        cell = logic.findCellByXY(s.x - 1, s.y + 2);
        if (cell != null) {
            allowedMoves[i] = cell;
            i++;
        }
        cell = logic.findCellByXY(s.x - 2, s.y + 1);
        if (cell != null) {
            allowedMoves[i] = cell;
            i++;
        }
        cell = logic.findCellByXY(s.x - 2, s.y - 1);
        if (cell != null) {
            allowedMoves[i] = cell;
            i++;
        }
        cell = logic.findCellByXY(s.x - 1, s.y - 2);
        if (cell != null) {
            allowedMoves[i] = cell;
        }
        return Arrays.copyOf(allowedMoves, i + 1);
    }
}
