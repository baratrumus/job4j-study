package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {

    @Test
    public void whenAnalizeFileIsItsAnalized() {
        Analize analize = new Analize();
        StringJoiner strj = new StringJoiner(System.lineSeparator());
        try (PrintWriter out = new PrintWriter(new FileOutputStream("source.log"))) {
            out.println("200 10:56:01");
            out.println("strj");
            out.println("500 10:57:01");
            out.println("strj");
            out.println("400 10:58:01");
            out.println("strj");
            out.println("200 10:59:01");
            out.println("strj");
            out.println("500 11:01:02");
            out.println("strj");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }

        analize.unavailable("source.log", "target.log");

        try (BufferedReader read = new BufferedReader(new FileReader("target.log"))) {
            assertThat(read.readLine(), is("10:57:01;10:59:01;"));
            assertThat(read.readLine(), is("11:01:02;11:02:02;"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
