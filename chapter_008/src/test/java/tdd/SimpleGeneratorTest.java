package tdd;


import org.junit.Test;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class SimpleGeneratorTest {
    SimpleGenerator sg = new SimpleGenerator();

    @Test
    public void whenTwoValuesAndTwoKeysItsOk() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> values = Map.of("name", "Petr", "subject", "you");
        String expected = "I am a Petr, Who are you?";
        assertThat(sg.generate(template, values), is(expected));
    }

    @Test
    public void whenTheSameKeysItsOk() {
        String template = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> values = Map.of("sos", "Aaa");
        String expected = "Help, Aaa, Aaa, Aaa";
        assertThat(sg.generate(template, values), is(expected));
    }

    @Test(expected = RuntimeException.class)
    public void whenKeysAndValuesDifferItsExceptions() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> values = Map.of("name", "Petr");
        String expected = "I am Petr, Who are you?";
        assertThat(sg.generate(template, values), is(expected));
    }

}
