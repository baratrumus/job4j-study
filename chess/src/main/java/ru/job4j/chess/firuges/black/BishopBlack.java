package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }


    /** Ходы слона
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
        int i = 0;
        if (logic.isDiagonal(source, dest)) {
             int deltaMove = Math.abs(dest.x - source.x);
             if ((source.x < dest.x) && (source.y > dest.y)) {                    //ход вправо вверх
                 deltaX = 1;
                 deltaY = -1;
             } else if ((source.x < dest.x) && (source.y < dest.y)) {              //ход вправо вниз
                 deltaX = 1;
                 deltaY = 1;
             } else if ((source.x > dest.x) && (source.y > dest.y)) {              //ход влево вверх
                    deltaX = -1;
                    deltaY = -1;
             } else if ((source.x > dest.x) && (source.y < dest.y)) {              //ход влево вниз
                    deltaX = -1;
                    deltaY = 1;
             }

            for (i = 0; i < deltaMove; i++) {
                tmpX += deltaX;
                tmpY += deltaY;
                steps[i] = logic.findCellByXY(tmpX, tmpY);
                System.out.println(i + "  " + steps[i].x + "  " + steps[i].y);
            }
        } else {
            throw new ImpossibleMoveException("Слон так не ходит");
        }

        return Arrays.copyOf(steps, i);
    }


    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
