package ru.job4j.chess.firuges.white;

import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.GetKnightMoves;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Ilya Ivannikov
 * @version $Id$
 * @since 0.1
 */
public class KnightWhite implements Figure {
    private final Cell position;
    private final String figureName;
    private final GetKnightMoves knightMoves = new GetKnightMoves();

    public KnightWhite(final Cell position) {
        this.position = position;
        figureName = "Конь";
    }

    @Override
    public Cell position() {
        return this.position;
    }

    /** Ходы коня
     * @param source задает исходную ячейку
     * @param dest задает ячейку, куда следует пойти
     * @return  Если фигура может туда пойти - возвращает массив ячеек пути
     * @throws ImpossibleMoveException
     */
    @Override
    public Cell[] way(Cell source, Cell dest)  throws ImpossibleMoveException {
        Cell[] steps = new Cell[0];
        Cell[] allowedMoves = knightMoves.getKnightMoves(source);
        for (Cell cell : allowedMoves) {
            if ((cell.x == dest.x) && (cell.y == dest.y)) {
                steps = new Cell[]{dest};
                break;
            }
        }
        if (steps.length == 0) {
            throw new ImpossibleMoveException("Конь так не ходит");
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightWhite(dest);
    }

    @Override
    public void moveInfo(Cell source, Cell dest) {
        System.out.format("%s пошёл %s - %s \n", figureName, source, dest);
    }
}
