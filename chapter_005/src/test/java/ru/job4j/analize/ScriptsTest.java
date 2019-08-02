package ru.job4j.analize;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ScriptsTest {

    @Test
    public void whenGivenScriptMapFoundDependencies() {

        Map<Integer, List<Integer>> dp = Map.of(1, List.of(2, 3),
                                                2, List.of(4),
                                                3, List.of(4, 5),
                                                4, List.of(),
                                                5, List.of(),
                                                6, List.of(3, 5),
                                                7, List.of(4),
                                                8, List.of(6, 2),
                                                9, List.of(7, 5));

        Scripts scr = new Scripts(dp);
        List<Integer> result = scr.load(6);
        List<Integer> template = List.of(3, 4, 5);
        assertTrue(result.equals(template));

    }
}
