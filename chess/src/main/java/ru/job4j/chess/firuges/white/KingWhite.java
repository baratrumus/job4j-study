package ru.job4j.chess.firuges.white;

import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
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
        if ((source.y == dest.y + 1)
                || (source.y == dest.y - 1)
                || (source.x == dest.x + 1)
                || (source.x == dest.x - 1)) {
            steps = new Cell[] {dest};
        } else {
            throw new ImpossibleMoveException("Король так не ходит");
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingWhite(dest);
    }

    @Override
    public void moveInfo(Cell source, Cell dest) {
        System.out.format("%s пошёл %s - %s", figureName, source, dest);
    }
}
