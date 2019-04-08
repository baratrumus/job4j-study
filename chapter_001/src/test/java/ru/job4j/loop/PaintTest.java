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
}



