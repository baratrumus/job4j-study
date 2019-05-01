package ru.job4j.pseudo;


import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {
    @Test
    public void whenDrawSquare() {
        Shape sq = new Square();
        assertThat(
                sq.draw(),
                is(
                        new StringBuilder()
                                .append("+++++++")
                                .append("+     +")
                                .append("+     +")
                                .append("+++++++")
                                .toString()
                )
        );
    }
}