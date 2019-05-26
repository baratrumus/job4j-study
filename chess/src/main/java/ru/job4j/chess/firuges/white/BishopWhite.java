package ru.job4j.chess.firuges.white;

import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopWhite implements Figure {
    private final Cell position;
    private final String figureName;

    public BishopWhite(final Cell position) {
        this.position = position;
        figureName = "Слон";
    }

    @Override
    public Cell position() {
        return this.position;
    }

    /** Ходы  слона
     * @param source задает исходную ячейку
     * @param dest задает ячейку, куда следует пойти
     * @return  Если фигура может туда пойти - возвращает массив ячеек пути
     * @throws ImpossibleMoveException
     */
    @Override
    public Cell[] way(Cell source, Cell dest)  throws ImpossibleMoveException {
        Cell[] steps = new Cell[7];
        int tmpX = source.x;
        int tmpY = source.y;
        int i;
        if (!LINE_TYPE.isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("Слон так не ходит");
        }
        int deltaX = DELTAS.getDiagonalDeltas(source, dest)[0];
        int deltaY = DELTAS.getDiagonalDeltas(source, dest)[1];
        int deltaMove = DELTAS.getDiagonalDeltas(source, dest)[2];
        for (i = 0; i < deltaMove; i++) {
            tmpX += deltaX;
            tmpY += deltaY;
            steps[i] = LOGIC.findCellByXY(tmpX, tmpY);
        }
        return Arrays.copyOf(steps, i);
    }


    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }

    @Override
    public void moveInfo(Cell source, Cell dest) {
        System.out.format("%s пошёл %s - %s \n", figureName, source, dest);
    }
}
