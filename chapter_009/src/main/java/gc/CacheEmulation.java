package gc;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;


/**
 Создать структуру данных типа кеш. Кеш должен быть абстрактный. То есть необходимо, что бы можно
 было задать ключ получения объекта кеша и в случае если его нет в памяти, задать поведение загрузки
 этого объекта в кеш.
 Создать программу эмулирующее поведение данного кеша. Программа должна считывать текстовые файлы из системы
 и выдавать текст при запросе имени файла. Если в кеше файла нет. Кеш должен загрузить себе данные.
 По умолчанию в кеше нет ни одного файла. Текстовые файл должны лежать в одной директории.
 Пример. Names.txt, Address.txt - файлы в системе. При запросе по ключу Names.txt - кеш должен вернуть
 содержимое файла Names.txt.


 */
public class CacheEmulation {

    private HashMap<String, SoftReference<String>> cache = new HashMap<>();
    private String filePath;

    /**
     * @param path  путь до файла без имени самого файла
     */
    public CacheEmulation(String path) {
        this.cache = new HashMap<>();
        this.filePath = path;
    }

    /**
     *
     * @param key - file name
     * @return content of the file
     */
    public String getData(String key) {

        SoftReference<String> data = cache.get(key);

        if (data == null) {
            String fileData = "";
            try {
                fileData = Files.readString(Paths.get(filePath + File.separator + key));
            } catch (IOException e) {
                e.printStackTrace();
            }
            data = new SoftReference<String>(fileData);
            cache.put(key, data);
        }
        return data.get();
    }
}
