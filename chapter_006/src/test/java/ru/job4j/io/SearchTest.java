package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SearchTest {

    @Test
    public void whenConfigFileIsItsRead() {
        Search search = new Search();
        List<String> exts = List.of("txt", "php");
        String tmpDir = System.getProperty("java.io.tmpdir");

        File dir = new File(tmpDir + "TESTDIR");
        boolean created = dir.mkdir();
        tmpDir += "TESTDIR";
        File dir1 = new File(tmpDir + "/dir1");
        created = dir1.mkdir();
        File dir2 = new File(tmpDir + "/dir2");
        created = dir2.mkdir();
        File dir3 = new File(tmpDir + "/dir2/dir3");
        created = dir3.mkdir();

        File file1 = new File(tmpDir + "/dir1/file1.txt");
        File file2 = new File(tmpDir + "/dir1/file2.bmp");
        File file3 = new File(tmpDir + "/dir2/file3.php");
        File file4 = new File(tmpDir + "/dir2/file4.jpg");
        File file5 = new File(tmpDir + "/dir2/dir3/file5.odd");
        File file6 = new File(tmpDir + "/dir2/dir3/file6.php");
        try {
            created = file1.createNewFile();
            created = file2.createNewFile();
            created = file3.createNewFile();
            created = file4.createNewFile();
            created = file5.createNewFile();
            created = file6.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        List<File> files = search.filterExtentions(tmpDir, exts);
        List<File> template = List.of(file2, file4, file5);
        assertThat(files, is(template));

    }
}
