package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.exception.FigureNotFoundException;
import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.exception.OccupiedWayException;
import ru.job4j.chess.firuges.Figure;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev & Ilya Ivannikov
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;








    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }


    /**
     *
     * @param steps массив ячеек, через которые идет фигура, без начальной, но с конечной
     *              у пешки, короля, коня будет одна - конечная
     * @return  может ли она пройти
     * @throws OccupiedWayException
     */
    private boolean isWayFree(Cell[] steps) throws OccupiedWayException {
        boolean res = true;
        for (Cell cell : steps) {
           for (Figure fig : figures) {
               if ((cell.x == fig.position().x) && (cell.y == fig.position().y)) {
                    res = false;
                    throw new OccupiedWayException("Так пойти нельзя. Путь занят.");
               }
           }
        }
        return res;
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
        try {
            int index = this.findBy(source);
            if (index != -1) {
                steps = this.figures[index].way(source, dest);

                /*System.out.println(steps.length);
                for (Cell cell : steps) {
                    System.out.println(cell);
                }*/

                if (isWayFree(steps)) {
                    if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                        rst = true;
                        this.figures[index] = this.figures[index].copy(dest);
                        this.figures[index].moveInfo(source, dest);
                    }
                }
            }

        } catch (ImpossibleMoveException ime) {
            System.out.println(ime.getMessage());
        } catch (FigureNotFoundException ffe) {
            System.out.println(ffe.getMessage());
        } catch (OccupiedWayException owe) {
            System.out.println(owe.getMessage());
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
    private int findBy(Cell cell) throws FigureNotFoundException {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        if (rst == -1) {
            throw new FigureNotFoundException("Фигура не найдена");
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
