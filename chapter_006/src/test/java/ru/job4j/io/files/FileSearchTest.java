package ru.job4j.io.files;

import org.apache.commons.cli.ParseException;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.io.files.Args;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FileSearchTest {
    private static final String LS = System.getProperty("line.separator");

    @Test
    @Ignore
    public void fileSearchTest()  throws ParseException, IOException {

        String separator = File.separator; // символ /
        String tempDir = System.getProperty("java.io.tmpdir");
        String searchDir = tempDir + "SearchDir";
        String logDir = tempDir + separator + "SearchLog";
        File dirSearch = new File(searchDir);
        boolean created = dirSearch.mkdir();
        File dirLog = new File(logDir);
        created = dirSearch.mkdir();

        File dir1 = new File(dirSearch, "dir1");
        created = dir1.mkdir();
        File dir2 = new File(dirSearch, "dir2");
        created = dir2.mkdir();

        File tempFile = new File(searchDir + separator + "bob.txt");
        File tempFile2 = new File(searchDir +  separator + "dir1" + separator + "bob.php");
        File tempFile3 = new File(searchDir +  separator + "dir2" +  separator + "mob.java");
        try {
            tempFile.createNewFile();
            tempFile2.createNewFile();
            tempFile3.createNewFile();
        } catch (IOException ex) {
            System.err.println("An IOException was caught: " + ex.getMessage());
            ex.printStackTrace();
        }

        String[] expected = new String[2];
        expected[0] = searchDir + separator + "bob.txt";
        expected[1] = searchDir + separator + "dir1" + separator + "bob.php";


        File log = new File(logDir + separator + "log.txt");
        String[] args = {"-d", searchDir, "-n", "bob", "-m", "-o", "log.txt"};

        Args arguments = new Args(args);
        FileSearch fs = new FileSearch();
        fs.findFiles(arguments);

        String line;
        String[] result = new String[2];
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(log))) {
            while ((line = br.readLine()) != null) {
                result[i++] = line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(result, is(expected));

    }
}
