package ru.job4j.io.files;


import ru.job4j.io.Search;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 1. Создать программу для поиска файла.
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражение(не обязательно).
 * 4. Программа должна собираться в jar и запускаться через java -jar find.jar -d c:/ -n *.txt -m -o log.txt
 * -d d:/#Java/Programms/test -n *.txt -m -o log.txt
 *    Ключи
 *    -d - директория в которая начинать поиск.
 *    -n - имя файл, маска, либо регулярное выражение.
 *    -m - искать по макс, либо -f - полное совпадение имени. -r регулярное выражение.
 *    -o - результат записать в файл.
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 */

public class FileSearch {
    private Search search = new Search();
    String separator = File.separator;
    private static final String LS = System.getProperty("line.separator");

    /**
     *   allFiles - лист всех файлов из директории поиска, c поддиректориями
     */
    public void findFiles(Args args) throws IOException {
        List<File> resList = new ArrayList<>();
        String beginDir = args.directory();
        List<File> allFiles = search.files(beginDir);
        String maskOfFile = args.filename();
        File targetFile = new File(args.output());
        String searchType = args.searchMaskOrFullName();
        resList = findByMask(beginDir, allFiles, maskOfFile, searchType);
        writeResult(resList, args.output());
    }


    private void writeResult(List<File> list, String logFileName)  throws IOException {
        File logDir = new File(System.getProperty("java.io.tmpdir") + separator + "SearchLog");
        String logFile = logDir.getAbsolutePath() + separator + logFileName;
        //try {
            logDir.mkdir();
            //logFile.createNewFile();
       // } catch (IOException ex) {
          //  System.out.println(ex.getMessage());
        //}
        try (PrintWriter out = new PrintWriter(new FileOutputStream(logFile))) {
            for (File f : list) {
                out.println(f.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private List<File> findByMask(String beginDir, List<File> allFiles, String maskOfFile, String searchType) {
        List<File> resList = new ArrayList<>();
        Pattern regEx = Pattern.compile(maskOfFile, Pattern.CASE_INSENSITIVE);
        if ("m".equals(searchType)) {

           /* try (DirectoryStream<Path> dir = Files.newDirectoryStream(
                    Paths.get(beginDir), maskOfFile)) {
                    for (Path entry : dir) {
                        resList.add(entry.toFile());
                    }
            } catch (IOException ex) {
                System.err.println("An IOException was caught: " + ex.getMessage());
                ex.printStackTrace();

            }*/
             for (File file : allFiles) {
                String fileName = file.getName();
                if (fileName.contains(maskOfFile)
                        || fileName.equalsIgnoreCase(maskOfFile)
                        || fileName.endsWith(maskOfFile)
                        || fileName.startsWith(maskOfFile)) {
                    resList.add(file);
                }
            }
        } else if ("f".equals(searchType)) {
            for (File file : allFiles) {
                if (file.getName().contains(maskOfFile)) {
                    resList.add(file);
                }
            }
        } else if ("r".equals(searchType)) {
            for (File file : allFiles) {
                Matcher matcher = regEx.matcher(file.getName());
                if (matcher.matches()) {
                    resList.add(file);
                }
            }
        } else if (searchType != null) {
            System.out.println("Отсутствует ключ типа поиска");
        }
        return resList;
    }


    public static void main(String[] args) throws org.apache.commons.cli.ParseException, IOException {
        Args arguments = new Args(args);
        FileSearch filesearch = new FileSearch();
        filesearch.findFiles(arguments);
    }
}
