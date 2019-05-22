package ru.job4j.chess.firuges.black;

import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Ilya Ivannikov
 * @version $Id$
 * @since 0.1
 */
public class KingBlack implements Figure {
    private final Cell position;
    private final String figureName;


    public KingBlack(final Cell position) {
        this.position = position;
        figureName = "Король";
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] steps = new Cell[0];
        if ((source.y == dest.y + 1)
                || (source.y == dest.y - 1)
                || (source.x == dest.x + 1)
                || (source.x == dest.x - 1)) {
            steps = new Cell[]{dest};
        } else {
            throw new ImpossibleMoveException("Король так не ходит");
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingBlack(dest);
    }

    @Override
    public void moveInfo(Cell source, Cell dest) {
        System.out.format("%s пошёл %s - %s  \n", figureName, source, dest);
    }
}
