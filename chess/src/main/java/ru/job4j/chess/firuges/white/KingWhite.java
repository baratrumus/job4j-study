package ru.job4j.chess.firuges.white;

import ru.job4j.chess.GetKnightMoves;
import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Ilya Ivannikov
 * @version $Id$
 * @since 0.1
 */
public class KingWhite implements Figure {
    private final Cell position;
    private final String figureName;

    public KingWhite(final Cell position) {
        figureName = "Король";
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] steps = new Cell[0];
        if (!((source.y - 1 == dest.y && source.x - 1 == dest.x)
                || (source.y - 1 == dest.y && source.x == dest.x)
                || (source.y - 1 == dest.y && source.x + 1 == dest.x)
                || (source.y == dest.y && source.x + 1 == dest.x)
                || (source.y + 1 == dest.y && source.x + 1 == dest.x)
                || (source.y + 1 == dest.y && source.x - 1 == dest.x)
                || (source.y + 1 == dest.y && source.x == dest.x)
                || (source.y == dest.y && source.x - 1 == dest.x))) {
            throw new ImpossibleMoveException("Король так не ходит");
        }
        steps = new Cell[] {dest};
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingWhite(dest);
    }

    @Override
    public void moveInfo(Cell source, Cell dest) {
        System.out.format("%s пошёл %s - %s \n", figureName, source, dest);
    }
}
