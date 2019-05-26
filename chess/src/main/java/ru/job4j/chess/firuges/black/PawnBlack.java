package ru.job4j.chess.firuges.black;

import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * @author Ilya Ivannikov
 * @version $Id$
 * @since 0.1
 */
public class PawnBlack implements Figure {
    private final Cell position;
    private final String figureName;

    public PawnBlack(final Cell position) {
        this.position = position;
        figureName = "Пешка";
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] steps = new Cell[0];
        if (!(source.y == dest.y + 1 && source.x == dest.x)) {
            throw new ImpossibleMoveException("Пешка так не ходит");
        }
        steps = new Cell[] {dest};
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }

    @Override
    public void moveInfo(Cell source, Cell dest) {
        System.out.format("%s пошла %s - %s  \n", figureName, source, dest);
    }
}
