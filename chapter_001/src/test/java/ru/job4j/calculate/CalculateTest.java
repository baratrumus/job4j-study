package ru.job4j.calculate;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Package for test calculate.
*
* @author Ilya    Ivannikov (baratrumus@yandex.ru)
* @version $Id$
* @since 0.1
*/

public class CalculateTest {
	 /**
	  * Test echo.
	  */
	@Test
	public void whenTakeNameThenThreeEchoPlusName() {
	String input = "Privet";
	String expect = "Echo, echo, echo : Privet";
	Calculate calc = new Calculate();
	String result = calc.echo(input);
	assertThat(result, is(expect));
	}
}