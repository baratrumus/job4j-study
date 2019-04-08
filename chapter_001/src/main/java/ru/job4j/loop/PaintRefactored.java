package ru.job4j.loop;
import java.util.function.BiPredicate;

/**
 * Class Класс рисования пирамиды  псевдосимволов с использованием BiPredicate
 * @author ivannikov
 * @version $Id$
 * @since 0.1
 */
public class PaintRefactored {
    /**
     *
     * @param height высота пирамиды
     * @return строка StringBuilder выводящая пирамиду
     */


    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

    private String loopBy(int height, int width, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            if (row < height - 1) {
                screen.append(System.lineSeparator());
            }
        }
        return screen.toString();
    }
}
