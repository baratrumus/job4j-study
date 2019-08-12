package ru.job4j.io;

import java.io.File;
import java.util.*;

public class Search {
    /**
     *  метод, возвращает список всех файлов из диpектории
     *  Метод должен заходить во всех каталоги.
     * Для этого нужно использовать алгоритм обхода дерева в ширину.
     * @param parent путь до каталога, с которого нужно осуществлять поиск.
     */

    private List<File> files(String parent) {
        List<File> resFiles = new ArrayList<>();
        File dir = new File(parent);
        Queue<File> data = new LinkedList<>();
        String filename = "";
        data.offer(dir);
        while (!data.isEmpty()) {
            File fileOrDir = data.poll();
            if ((fileOrDir != null) && (!fileOrDir.isDirectory())) {
               resFiles.add(fileOrDir);
            } else if (fileOrDir != null) {
                for (File item : fileOrDir.listFiles()) {
                    data.offer(item);
                }
            }
        }
        return resFiles;
    }


    /**
     *  метод фильтрует расширения файлов по списку
     * @param source  имя папки
     * @param excludeExts список расширений на исключение
     * @return
     */
    public List<File> filterExtentions(String source, List<String> excludeExts) {
        List<File> res = new ArrayList<>();
        List<File> fileStructure = files(source);
        boolean allowedFile;
        for (File f : fileStructure) {
            allowedFile = true;
            for (String str : excludeExts) {
                if (f.getName().contains(str)) {
                    allowedFile = false;
                    break;
                }
            }
            if (allowedFile) {
                res.add(f);
            }
        }
        return res;
    }
}
