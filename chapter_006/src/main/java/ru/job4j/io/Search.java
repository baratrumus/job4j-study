package ru.job4j.io;

import java.io.File;
import java.util.*;

public class Search {
    /**
     *  метод, который возвращает список всех файлов с конкретным расширением.
     *  Метод должен заходить во всех каталоги.
     * Для этого нужно использовать алгоритм обхода дерева в ширину.
     * @param parent путь до каталога, с которого нужно осуществлять поиск.
     * @param exts  это расширения файлов, которые мы ходим получить.
     */
    List<File> files(String parent, List<String> exts) {
        List<File> resFiles = new ArrayList<>();
        File dir = new File(parent);
        Queue<File> data = new LinkedList<>();
        String filename = "";
        data.offer(dir);
        while (!data.isEmpty()) {
            File fileOrDir = data.poll();
            if (!fileOrDir.isDirectory()) {
                filename = fileOrDir.getName();
                if (correctExtention(filename, exts)) {
                    resFiles.add(fileOrDir);
                }
            } else {
                for (File item : fileOrDir.listFiles()) {
                    data.offer(item);
                }
            }
        }
        return resFiles;
    }


    /**
     * метод проверяет входит ли расширение файла в список интересующих нас
     */
    private boolean correctExtention(String filename, List<String> exts) {
        boolean res = false;
        String[] extention;
        extention = filename.split("\\.");
        if (exts.contains(extention[1])) {
            res = true;
        }
        return res;
    }
}
