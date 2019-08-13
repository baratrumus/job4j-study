package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.apache.commons.cli.ParseException;
import org.junit.Ignore;

import java.io.File;
import java.io.IOException;

public class ZipTest {

    @Test
    @Ignore
    public void zipTest()  throws ParseException {

        String tmpdir = System.getProperty("java.io.tmpdir");
        File tempFile = null;
        File tempFile2 = null;
        File tempFile3 = null;
        File tempFile4 = null;
        File directory = new File(tmpdir + "/ForZip");
        boolean created = directory.mkdir();
        File directory1 = new File(tmpdir + "/ResZip");
        created = directory1.mkdir();

        try {
            //Create two temporary files.
            tempFile = File.createTempFile("bob", ".txt", directory);
            tempFile2 = File.createTempFile("pit", ".php", directory);
            tempFile3 = File.createTempFile("mob", ".java", directory);
            tempFile4 = File.createTempFile("pet", ".jpg", directory);
        } catch (IOException ex) {
            System.err.println("An IOException was caught: " + ex.getMessage());
            ex.printStackTrace();
        }

        String log = tmpdir + "ResZip/ZipTest.zip";
        var parameters = new String[7];
        parameters[0] = "-d";
        parameters[1] = tmpdir + "ForZip";
        parameters[2] = "-o";
        parameters[3] = tmpdir + "ResZip/ZipTest.zip";
        parameters[4] = "-e";
        parameters[5] = "java";
        parameters[6] = "php";
        Args args = new Args(parameters);
        Zip zip = new Zip();
        zip.pack(args);
        assertThat(new File((log)).exists(), is(true));

    }
}
