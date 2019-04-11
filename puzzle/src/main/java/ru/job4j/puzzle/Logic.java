package ru.job4j.puzzle;

import ru.job4j.puzzle.firuges.Cell;
import ru.job4j.puzzle.firuges.Figure;


/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final int size;
    private final Figure[] figures;
    private int index = 0;

    public Logic(int size) {
        this.size = size;
        this.figures = new Figure[size * size];
    }

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            if (this.isFree(steps)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
    }

    public boolean isFree(Cell ... cells) {
        boolean result = cells.length > 0;
        for (Cell cell : cells) {
            if (this.findBy(cell) != -1) {
               result = false;
               break;
            }
        }
        return result;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

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
     *  В массиве int[][] table содержаться 0 и 1. Метод проверяет что в массиве единицы образуют
     *  полный ряд по вертикали или горизонтали.
     * @return возвращает true если такой ряд есть
     */
    public boolean isWin() {
        int[][] table = this.convert();
        boolean horWin = false;
        boolean vertWin = false;
        int size = table.length;
        for (int row = 0; row < size; row++) {
            if (table[row][0] == 1) {
                horWin = true;
            }
            if (table[0][row] == 1) {
                vertWin = true;
            }

            if ((table[row][0] == 1) || (table[0][row] == 1)) {

                for (int col = 0; col < size - 1; col++) {
                    if (table[row][col] != table[row][col + 1]) { // проверка горизонтальной победы
                        horWin = false;
                    }
                    //проверяем вертикальную победу, row используем как коллонки, col ряды
                    if (table[col][row] != table[col + 1][row]) {
                        vertWin = false;
                    }
                }
            }
        }
        return (horWin || vertWin);
    }


    public int[][] convert() {
        int[][] table = new int[this.size][this.size];
        for (int row = 0; row != table.length; row++) {
            for (int cell = 0; cell != table.length; cell++) {
                int position = this.findBy(new Cell(row, cell));
                if (position != -1 && this.figures[position].movable()) {
                    table[row][cell] = 1;
                }
            }
        }
        return table;
    }
}
