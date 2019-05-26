package ru.job4j.chess.firuges.white;

import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;

/**
 *
 * @author Ilya Ivannikov
 * @version $Id$
 * @since 0.1
 */
public class QeenWhite implements Figure {
    private final Cell position;
    private final String figureName;

    public QeenWhite(final Cell position) {
        this.position = position;
        figureName = "Ферзь";
    }

    @Override
    public Cell position() {
        return this.position;
    }

    /** Ходы ферзя
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
        int deltaX = 0;
        int deltaY = 0;
        int deltaMove = 0;
        int i;
        if (LINE_TYPE.isDiagonal(source, dest)) {
            deltaX = DELTAS.getDiagonalDeltas(source, dest)[0];
            deltaY = DELTAS.getDiagonalDeltas(source, dest)[1];
            deltaMove = DELTAS.getDiagonalDeltas(source, dest)[2];
        } else if (LINE_TYPE.isLine(source, dest)) {
            deltaX = DELTAS.getHorizontalDeltas(source, dest)[0];
            deltaY = DELTAS.getHorizontalDeltas(source, dest)[1];
            deltaMove = DELTAS.getHorizontalDeltas(source, dest)[2];
        } else {
            throw new ImpossibleMoveException("Ферзь так не ходит");
        }
        for (i = 0; i < deltaMove; i++) {
            tmpX += deltaX;
            tmpY += deltaY;
            steps[i] = LOGIC.findCellByXY(tmpX, tmpY);
            //System.out.println(i + "  " + steps[i].x + "  " + steps[i].y);
        }
        return Arrays.copyOf(steps, i);
    }

    @Override
    public Figure copy(Cell dest) {
        return new QeenWhite(dest);
    }

    @Override
    public void moveInfo(Cell source, Cell dest) {
        System.out.format("%s пошёл %s - %s \n", figureName, source, dest);
    }
}
