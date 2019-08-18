package ru.job4j.io.files;

import ru.job4j.io.Args;
import ru.job4j.io.Search;
import ru.job4j.io.Zip;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 1. Создать программу для поиска файла.
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражение(не обязательно).
 * 4. Программа должна собираться в jar и запускаться через java -jar find.jar -d c:/ -n *.txt -m -o log.txt
 *    Ключи
 *    -d - директория в которая начинать поиск.
 *    -n - имя файл, маска, либо регулярное выражение.
 *    -m - искать по макс, либо -f - полное совпадение имени. -r регулярное выражение.
 *    -o - результат записать в файл.
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 */

public class FileSearch {
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
        int directoryLength = args.directory().length() + 1;
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)), UTF_8)) {

            for (File file : zipList) {
                String asbPath = file.getAbsolutePath();
                String path = asbPath.substring(directoryLength);
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
