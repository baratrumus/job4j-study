package ru.job4j.loop;

/**
 * Class Класс рисования матрицы - шахм. доски из символов " " и X
 * @author ivannikov
 * @since 05.04.2019
 * @version 1
 */
public class Board {
    /**
     * lineSeparator перевод на новую строку.
     * @param width ширина матрицы
     * @param height высота матрицы
     * @return строка StringBuilder выводящая матрицу
     */

    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}