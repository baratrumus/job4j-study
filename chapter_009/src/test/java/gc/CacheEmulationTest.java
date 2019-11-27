package gc;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CacheEmulationTest {
    private final static String LN = System.lineSeparator();
    private final static String FS = File.separator;    // simbol /

    @Test
    public void whenNotInCacheLoadtoCacheButReadFromFile() throws IOException {

        String expected = "milk" + LN + "bread" + LN + "butter";
        File file = File.createTempFile("food", ".txt");
        Files.write(file.toPath(), expected.getBytes());

        String name = file.getName();
        String absPath = file.getAbsolutePath();
        String path = absPath.substring(0, absPath.lastIndexOf(FS));

        CacheEmulation cache = new CacheEmulation(path);
        assertThat(cache.getData(name), is(expected));
    }

    @Test
    public void whenFileAlreadyInCacheReadFromCache() throws IOException {

        String expected = "milk" + LN + "bread" + LN + "butter";
        File file = File.createTempFile("food", ".txt");
        Files.write(file.toPath(), expected.getBytes());

        String name = file.getName();
        String absPath = file.getAbsolutePath();
        String path = absPath.substring(0, absPath.lastIndexOf(FS));

        CacheEmulation cache = new CacheEmulation(path);
        assertThat(cache.getData(name), is(expected)); // первый раз кладем файл в кэш
        Files.write(file.toPath(), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);  //опустошаем файл
        assertThat(Files.readString(file.toPath()), is(""));    //файл пустой
        assertThat(cache.getData(name), is(expected));  //значит данные тянутся из кэша
    }
}