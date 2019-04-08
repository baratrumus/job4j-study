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
public class PaintRefactoredTest {
    @Test
    public void whenPyramidIs4() {
        PaintRefactored paint = new PaintRefactored();
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



