package ru.job4j.loop;

/**
 * Class Класс рисования пирамиды  псевдосимволов
 * @author ivannikov
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    /**
     *
     * @param height высота пирамиды
     * @return строка StringBuilder выводящая пирамиду
     */
    public String pyramid(int height) {
        String ln = System.lineSeparator();

        StringBuilder screen = new StringBuilder();
        int width = height * 2 - 1;

        for (int row = 0; row < height; row++) {
            // внутренний цикл определяет положение ячейки в строке.
            for (int column = 0; column < width; column++) {
                // если строка равна ячейке, то рисуем галку.
                // в данном случае строка определяет, сколько галок будет в строке
                if ((row >= height - column - 1) && (row + height > column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод строки, кроме последней
            if (row < height - 1) {
                screen.append(ln);
            }

            //System.out.printf(screen.toString());
        }
        // Получаем результат.
        return screen.toString();
    }
}