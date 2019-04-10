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
}