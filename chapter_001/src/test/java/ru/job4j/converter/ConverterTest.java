package ru.job4j.converter;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Package for test converter.
 *
 * @author Ilya Ivannikov (baratrumus@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class ConverterTest {
    @Test
    public void when120RubleToDollarThen2() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(120);
        assertThat(result, is(2));
    }

    @Test
    public void when140RubleToEuroThen2() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(140);
        assertThat(result, is(2));
    }

    @Test
    public void when3EuroToRubleThen210() {
        Converter converter = new Converter();
        int result = converter.euroToRuble(3);
        assertThat(result, is(210));
    }

    @Test
    public void when3DollarToRubleThen180() {
        Converter converter = new Converter();
        int result = converter.dollarToRuble(3);
        assertThat(result, is(180));
    }

}