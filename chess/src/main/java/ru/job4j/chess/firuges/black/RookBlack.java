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
public class RookBlack implements Figure{
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
        int i =0;
        if (logic.isLine(source, dest)) {
            if(source.x == dest.x) {
                if (source.y < dest.y) {                    //ход вертикально вниз
                    tmpY++;                    //убираем из массива исходную точку
                    for(i = 0;tmpY <= dest.y;i++, tmpY++) {
                        steps[i] = logic.findCellByXY(source.x, tmpY);
                    }
                }else if (source.y > dest.y) {              //ход вертикально вверх
                    tmpY--;
                    for(i = 0;tmpY >= dest.y;i++, tmpY--) {
                        steps[i] = logic.findCellByXY(source.x, tmpY);
                       // System.out.println(i + "  " + steps[i].x + "  " +steps[i].y);
                    }
                }
            }else if(source.y == dest.y) {
                if (source.x < dest.x) {                     //ход вправо
                    tmpX++;
                    for (i = 0; tmpX <= dest.x; i++, tmpX++) {
                        steps[i] = logic.findCellByXY(tmpX, source.y);
                    }
                } else if (source.x > dest.x) {              //ход влево
                    tmpX--;
                    for (i = 0; tmpX >= dest.x; i++, tmpX--) {
                        steps[i] = logic.findCellByXY(tmpX, source.y);
                    }
                }
            }
        }else throw new ImpossibleMoveException("Тура так не ходит");

        return Arrays.copyOf(steps, i);
    }



@Override
public Figure copy(Cell dest) {
    return new RookBlack(dest);
}
}
