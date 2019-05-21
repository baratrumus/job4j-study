package ru.job4j.chess.firuges.white;

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
public class RookWhite implements Figure {
    private final Cell position;

    public RookWhite(final Cell position) {
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
        int deltaX = 0;
        int deltaY = 0;
        int deltaMove = 0;
        int i = 0;
        if (LOGIC.isLine(source, dest)) {
            if (source.x == dest.x) {
                if (source.y < dest.y) {                    //ход вертикально вниз
                    deltaY = 1;
                    deltaMove = dest.y - source.y;
                } else if (source.y > dest.y) {              //ход вертикально вверх
                    deltaY = -1;
                    deltaMove = source.y - dest.y;
                }
            } else if (source.y == dest.y) {
                if (source.x < dest.x) {                     //ход вправо
                    deltaX = 1;
                    deltaMove = dest.x - source.x;
                } else if (source.x > dest.x) {              //ход влево
                    deltaX = -1;
                    deltaMove =  source.x - dest.x;
                }
            }

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
        return new RookWhite(dest);
    }
}
