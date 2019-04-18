package ru.job4j.loop;


/**
 * Class Класс рисования пирамиды  псевдосимволов
 * @author ivannikov
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    /**
     *  ширина будет равна высоте.
     * screen Буфер для результата.
     * @param height высота пирамиды
     * @return строка StringBuilder выводящая правую часть пирамиды
     */
    public String rightTrl(int height) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != height; column++) {
                if (row >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }


     /**
     * @param height высота пирамиды
     * @return строка StringBuilder выводящая левую часть пирамиды
     */
    public String leftTrl(int height) {
        StringBuilder screen = new StringBuilder();
        //int width = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != height; column++) {
                if (row >= height - column - 1) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }


    /**
     * @param height высота пирамиды
     * @return строка StringBuilder выводящая пирамиду
     */
    public String pyramid(int height) {
        String ln = System.lineSeparator();
        StringBuilder screen = new StringBuilder();
        int width = height * 2 - 1;
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if ((row >= height - column - 1) && (row + height > column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            // перевод строки, кроме последней
            if (row < height - 1) {
                screen.append(ln);
            }
        }
        return screen.toString();
    }
}