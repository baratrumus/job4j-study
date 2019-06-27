package ru.job4j.matrix;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MatrixTest {
    @Test
    public void whenGetProfilesTakeAdress() {
        Integer[][] matrixArr = {{1, 2}, {3, 4}};

        MatrixToList matrix = new MatrixToList();
        List<Integer> result = matrix.matrixToList(matrixArr);
        List<Integer> expected = List.of(1, 2, 3, 4);

        result.forEach(System.out::println);
        assertThat(result, is(expected));
    }
}
