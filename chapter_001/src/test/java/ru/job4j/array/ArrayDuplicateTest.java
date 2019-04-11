package ru.job4j.array;


import org.junit.Test;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        String[] input = {"cat", "cat", "dog", "mouse", "cat", "cat", "mouse", "dog", "bird", "mouse", "mouse"};
        String[] expect = {"cat", "dog", "mouse", "bird"};
        ArrayDuplicate removeDup = new ArrayDuplicate();
        String[] rst = removeDup.remove(input);
        assertThat(rst, arrayContainingInAnyOrder(expect));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate1() {
        String[] input = {"cat", "cat", "dog", "mouse", "cat", "cat", "mouse", "dog", "bird", "mouse", "mouse"};
        String[] expect = {"cat", "dog", "mouse", "bird"};
        ArrayDuplicate removeDup = new ArrayDuplicate();
        String[] rst = removeDup.removeDuplicate(input);
        assertThat(rst, arrayContainingInAnyOrder(expect));
    }


    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate3() {
        String[] input = {"1", "1", "1", "1", "1"};
        String[] expect = {"1"};
        ArrayDuplicate removeDup = new ArrayDuplicate();
        String[] rst = removeDup.removeDuplicate(input);
        assertThat(rst, arrayContainingInAnyOrder(expect));
    }
}