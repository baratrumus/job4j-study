package ru.job4j.loop;

import org.junit.Test;
import java.util.StringJoiner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Class Класс тест рисования пирамиды псевдосимволов
 * @author ivannikov
 * @since 0.1
 * @version $Id$
 */
public class PaintTest {
    @Test
    public void whenPyramidIs4() {
        Paint paint = new Paint();
        String rst = paint.pyramid(4);
        System.out.println(rst);
        StringJoiner joinPattern = new StringJoiner(System.lineSeparator());
        joinPattern.add("   ^   ");
        joinPattern.add("  ^^^  ");
        joinPattern.add(" ^^^^^ ");
        joinPattern.add("^^^^^^^");
        //joinPattern.add("\r");

        assertThat(rst, is(joinPattern.toString()));
    }

    @Test
    public void whenPyramid4Left() {
        Paint paint = new Paint();
        String rst = paint.leftTrl(4);
        System.out.println(rst);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("   ^")
                                .add("  ^^")
                                .add(" ^^^")
                                .add("^^^^")
                                .toString()
                )
        );
    }

    @Test
    public void whenPyramid4Right() {
        Paint paint = new Paint();
        String rst = paint.rightTrl(4);
        System.out.println(rst);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("^   ")
                                .add("^^  ")
                                .add("^^^ ")
                                .add("^^^^")
                                .toString()
                )
        );
    }


}



