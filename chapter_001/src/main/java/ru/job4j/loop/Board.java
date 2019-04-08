package ru.job4j.loop;


/**
 * Class Класс рисования матрицы из символов " " и X
 * @author ivannikov
 * @since 05.04.2019
 * @version 1
 */
public class Board {

    /**
     *
     * @param width ширина матрицы
     * @param height высота матрицы
     * @return строка StringBuilder выводящая матрицу
     */

    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // условие проверки, что писать пробел или X
                // Выше в задании мы определили закономерность, когда нужно проставлять X
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод на новую строку.
            screen.append(ln);
            //System.out.printf(screen.toString());
        }
        return screen.toString();
    }
}