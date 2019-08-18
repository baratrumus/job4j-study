package ru.job4j.io.consolechat;


import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConsoleChatTest {

    @Test
    public void whenChatBeginsLogCreated() {

        String separator = File.separator; // символ /
        String[] aiFrase =  {"У тебя все в порядке ?",
                            "Кажется дождик начинается",
                            "Пойдем гулять",
                            "Мне нравятся твои носки",
                            "Не хочу общаться",
                            "А ведь скоро новый год"};

        String[] inputFrase =  {"привет",
                "хорошо",
                "договорились",
                "finish"};

        String logFilePath = System.getProperty("java.io.tmpdir") + separator + "chat.log";
        File log = new File(logFilePath);
        if (log.exists()) {
            log.delete();
        }
        String chatSource = System.getProperty("java.io.tmpdir") + separator + "chatsource.txt";
        try (PrintWriter out = new PrintWriter(new FileOutputStream(chatSource))) {
            for (String str : aiFrase) {
                out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ConsoleChat chat = new ConsoleChat(new SimInput(inputFrase), chatSource, logFilePath);
        chat.init();

        List<String> logList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(logFilePath))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                logList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       assertThat(logList.get(3), is("Person : хорошо"));
    }
}
