package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     *  должен считать все ключи в карту values.
     *  в файле могут быть пустые строки и комментарии их нужно пропускать.
     */
   public void load() {
       StringJoiner out = new StringJoiner(System.lineSeparator());
       try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
           read.lines().forEach(line -> handleLine(line));
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    private  void handleLine(String line) {
       char [] chars = line.toCharArray ();
       String key = "";
       String value = "";
       boolean keyFull = false;
       boolean commentLine = false;
       if (!line.equals("")) {
            commentLine = ((chars[0] == '#') && (chars[1] == '#'));
       }
       if (!line.equals("") && !commentLine) {
           for (char ch : chars) {
               if ((ch != '=') && (!keyFull)) {
                   key += Character.toString(ch);
               } else if (ch == '=') {
                   keyFull = true;
                   continue;
               } else {
                   value += Character.toString(ch);
               }
           }
           values.put(key, value);
       }
    }

    /**
     * метод  должен возвращать значение ключа.
     */
    public String value(String key) {
       return  values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
