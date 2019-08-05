package ru.job4j.io;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 *  есть файл регистрации событий сервера source .
 Он имеет следующий формат TYPE date
 *Type - может иметь 4 значения 200, 300, 400, 500
 *Date - это время проверки 10:56:01 (формат часы:минуты:секунды)
 * target - имя файла после анализа.
 * Формат файла - начало периода;конец периода;
 */

public class Analize {
    private String beginTime = "";
    private String endTime = "";
    private Queue<String> times = new LinkedList<>();

    public Analize() { }

    /**
     * Метод anavailable должен находить диапазоны, когда сервер не работал.
     * Сервер не работал. если status = 400 или 500.
     * Начальное время - это время когда статус 400 или 500.
     * конечное время это когда статус меняется с 400 или 500 на 200 300.
     */

    public void unavailable(String source, String target) {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(line -> handleLine(line));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeToTarget(target);
    }

    private void writeToTarget(String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            while (!times.isEmpty()) {
                out.println(times.poll());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLine(String line) {
        if (!line.equals("")) {
            if (beginTime.equals("") && (line.contains("500") || line.contains("400"))) {
                beginTime = line.substring(4);
            } else if (!beginTime.equals("") && (line.contains("200") || line.contains("300"))) {
                endTime = line.substring(4);
            }
            if (!beginTime.equals("") && !endTime.equals("")) {
                times.offer(beginTime + ";" + endTime + ";");
                beginTime = "";
                endTime = "";
            }
        }
    }
}
