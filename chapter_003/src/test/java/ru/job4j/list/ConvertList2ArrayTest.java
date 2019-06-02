package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertList2ArrayTest {
    ConvertList2Array convertList = new ConvertList2Array();

    @Test
    public void when7ElementsThen9() {

        int[][] result = convertList.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when2And4ElementsThen6() {
        List<int[]> beginingList = new ArrayList<>();
        beginingList.add(new int[]{1, 2});
        beginingList.add(new int[]{3, 4, 5, 6});
        List<Integer> result = convertList.convert(beginingList);
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(result, is(expect));
    }
   }
