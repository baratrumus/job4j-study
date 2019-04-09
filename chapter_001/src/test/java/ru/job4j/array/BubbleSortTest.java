package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    /**
     * тест, проверяющий сортировку массива из 10 элементов методом пузырька.
     */
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {

        int[] sourceArr = new int[] {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        int[] sortedArr = new int[] {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        BubbleSort bs = new BubbleSort();
        int[] result = bs.sort(sourceArr);
        assertThat(result, is(sortedArr));
    }
}
