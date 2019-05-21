package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.*;
import ru.job4j.chess.firuges.Cell;

import java.util.Arrays;



/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class RookBlack implements Figure {
    private final Cell position;

    public RookBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    /** Ходы туры
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
        int i = 0;
        if (LOGIC.isLine(source, dest)) {
            int deltaX = LOGIC.getHorizontalDeltas(source, dest)[0];
            int deltaY = LOGIC.getHorizontalDeltas(source, dest)[1];
            int deltaMove = LOGIC.getHorizontalDeltas(source, dest)[2];

            for (i = 0; i < deltaMove; i++) {
                tmpX += deltaX;
                tmpY += deltaY;
                steps[i] = LOGIC.findCellByXY(tmpX, tmpY);
                System.out.println(i + "  " + steps[i].x + "  " + steps[i].y);
            }
        } else {
            throw new ImpossibleMoveException("Тура так не ходит");
        }

        return Arrays.copyOf(steps, i);
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }
}
