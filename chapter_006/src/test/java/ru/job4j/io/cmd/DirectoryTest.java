package ru.job4j.io.cmd;

import org.junit.Test;

import java.io.File;

public class DirectoryTest {

    @Test
    //@Ignore
    public void cmdTest() {
        final Shell shell = new Shell();
        String directory = shell.getDirectory();
        String separator = File.separator;

        File dirUsr = new File(directory + separator + "usr");
        boolean created = dirUsr.mkdir();
        File dirLib = new File(directory + separator + "lib");
        created = dirLib.mkdir();
        File dirLocal = new File(directory + separator + "usr" + separator + "local");
        created = dirLocal.mkdir();

        shell.cd("/");
        String r = shell.path();
        assert shell.path().equals("/");

        shell.cd("usr/..");
        r = shell.path();
        assert shell.path().equals("/");

        shell.cd("usr").cd("local");
        r = shell.path();
        shell.cd("../local").cd("./");
        r = shell.path();
        assert shell.path().equals("/usr/local");

        shell.cd("..");
        r = shell.path();
        assert shell.path().equals("/usr");

        shell.cd("//lib///");
        assert shell.path().equals("/lib");
    }
}

/*

1. нужно везде добавить модификаторы доступ.

        2. как сделать действия  в стиле ооп?

        3. как быть если я хочу добавить новое поведение в твой код?
*/