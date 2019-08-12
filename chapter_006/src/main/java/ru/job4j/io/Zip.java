package ru.job4j.io;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Zip {
    private Search search = new Search();;

    /**
     * zipList - сюда получаем уже отфильтрованный список файлов на архивацию
     * target - целевой файл
     * метод использует 3 аргумента ком. строки
     * args[0] директория которую архивируем,
     * args[1] файл вывода,
     * varargs  список исключаемых расширений
     * @param args
     */
    public void pack(Args args) {
        List<File> zipList = search.filterExtentions(args.directory(),  args.exclude());
        File target = new File(args.output());
        int directory_length = args.directory().length();
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)), UTF_8)) {

            for (File file : zipList) {
                String asbPath = file.getAbsolutePath();
                String path = asbPath.substring(directory_length);
                zip.putNextEntry(new ZipEntry(path));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
            }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws org.apache.commons.cli.ParseException  {
        Args arguments = new Args(args);
        Zip zipProcess = new Zip();
        zipProcess.pack(arguments);
    }
}
