package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Optional;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;


    public boolean isDiagonal(Cell source, Cell dest) {
        boolean ret = false;
        int deltaX = 0;
        int deltaY = 0;
        int tmpX = source.x;
        int tmpY = source.y;
        if (source.x < dest.x) {
            deltaX = 1;
        }else if (source.x > dest.x) {
            deltaX = -1;
        }else return ret;

        if (source.y < dest.y) {
            deltaX = 1;
        }else if (source.y > dest.y) {
            deltaX = -1;
        }else return ret;

        for(int i = 0;i < 7;i++) {
            tmpX += deltaX;
            tmpY += deltaY;
            if (tmpX == dest.x && tmpY == dest.y) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public boolean isLine(Cell source, Cell dest) {
        boolean ret = false;
        if (source.x == dest.x || source.y == dest.y) {
            ret = true;
        }
        return ret;
    }


    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * Если фигура в ячейке есть получаем массив ячеек пути.
     * Если он не пустой и последняя ячейка в нем равна dest
     * В массив по этому индексу помещаем новую фигуру с cell = dest
     * @param source
     * @param dest
     * @return
     */
    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        Cell[] steps;
        int index = this.findBy(source);
        if (index != -1) {
            try {
                steps = this.figures[index].way(source, dest);

                System.out.println(steps.length);
                for (Cell cell : steps) {
                     System.out.println(cell);
                 }

                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    rst = true;
                    this.figures[index] = this.figures[index].copy(dest);
                }
            }
            catch(ImpossibleMoveException ime){
                System.out.println(ime.getMessage());
            }
        }
        return rst;
    }

    /**
     * очистить массив позиций фигур
     */
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     *
     * @param cell получает ячейку
     * @return индекс фигуры в массиве
     */
    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    /**
     * По координатам ячеек
     * @param x
     * @param y
     * @return элемент enub
     */
    public Cell findCellByXY(int x, int y) {
        Cell rst = null;
        for (Cell cell : Cell.values()) {

            if (cell.x == x && cell.y == y) {
                rst = cell;
                break;
            }
        }

        return rst;
    }
}
