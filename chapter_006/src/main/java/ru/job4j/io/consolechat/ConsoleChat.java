package ru.job4j.io.consolechat;

import java.io.*;
import java.util.*;

/**
 * Создать программу 'Консольный чат'. Пользователь вводит слово-фразу, программа берет случайную фразу
 * из текстового файла и выводит в ответ. Программа замолкает если пользователь вводит слово «стоп»,
 * при этом он может продолжать отправлять сообщения в чат. Если пользователь вводит слово «продолжить»,
 * программа снова начинает отвечать. При вводе слова «закончить» программа прекращает работу.
 * Запись диалога включая, слова-команды стоп/продолжить/закончить записать в текстовый лог.
 */
public class ConsoleChat {
    private final Input input;
    private Queue<String> chatLines = new LinkedList<>();
    private List<String> answers = new ArrayList<>();
    private String chatSource;
    private String chatLog;
    private boolean exit;
    private boolean stop;

  public ConsoleChat(Input input, String chatSource, String chatLog) {
        this.chatSource = chatSource;
        this.chatLog = chatLog;
        this.exit = false;
        this.stop = false;
        this.input = input;
    }

    public static void main(String[] args) {
        String chatSource = System.getProperty("java.io.tmpdir") + "/chatsource.txt";
        String chatLog = System.getProperty("java.io.tmpdir") + "/chat.log";
        new ConsoleChat(new ConsoleInput(), chatSource, chatLog).init();
    }

    public void init() {
        String personLine;
        String aiLine = "Привет, давай общаться. Для паузы набери stop. Для продолжения go. Для выхода finish.";
        System.out.println(aiLine);
        writeToTarget(aiLine, "ai");

        do {
            aiLine = getAnswer();
            personLine = input.ask();

            if ("stop".equals(personLine)) {
                this.stop = true;
                aiLine = "ai отключен";
                System.out.println(aiLine);
            } else if ("go".equals(personLine)) {
                this.stop = false;
                aiLine = "ai включён";
            } else if ("finish".equals(personLine)) {
                this.exit = true;
                aiLine = "прощай";
            }

            if (stop) {
                writeToTarget(personLine, "Person");
            } else {
                writeToTarget(personLine, "Person");
                writeToTarget(aiLine, "ai");
                System.out.println(aiLine);
            }

        } while (!this.exit);


    }

    /**
     * source  исходный файл с фразами
     * @return случайную фразу
     */
    private String getAnswer() {
        if (answers.isEmpty()) {
            try (BufferedReader read = new BufferedReader(new FileReader(this.chatSource))) {
                read.lines().forEach(line -> answers.add(line));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Random random = new Random();
        int num = random.nextInt(answers.size());
        return answers.get(num);
    }



    private void writeToTarget(String mess, String role) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.chatLog, true))) {
            bw.write(role + " : " + mess + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
