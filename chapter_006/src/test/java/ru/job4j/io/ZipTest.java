package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.apache.commons.cli.ParseException;
import org.junit.Ignore;

import java.io.File;

public class ZipTest {

    @Test
    @Ignore
    public void zipTest()  throws ParseException{

        String log = "d:\\#Java\\Programms\\ZipJob4j.zip";
        var parameters = new String[7];
        parameters[0] = "-d";
        parameters[1] = "d:\\#Java\\Programms\\job4j\\";
        parameters[2] = "-o";
        parameters[3] = "ZipTest.zip";
        parameters[4] = "-e";
        parameters[5] = ".java";
        parameters[6] = ".php";
        Args args = new Args(parameters);
        Zip zip = new Zip();
        zip.pack(args);
        assertThat(new File((log)).exists(), is(true));

    }
}
