package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Меню - интерфейс  взаимодействия с пользователем
 * @author ivannikov
 * @version $Id$
 * @since 0.1
 */

public class StartUI {

    private final Input input;
    private final Tracker tracker;
    private boolean exit;
    /**
     * Конструктор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker, boolean exit) {
        this.input = input;
        this.tracker = tracker;
        this.exit = exit;
    }


    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {

        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker(), false).init();
    }


    /**
     * Основой цикл программы.
     */
    public void init() {

        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);
        List<Integer> menuRange = new ArrayList<>();
        for (int i = 0; i < menu.getActionsLength(); i++) {
            menuRange.add(i);
        }
        do {
            menu.show();
            menu.select(Integer.valueOf(input.ask("Введите пункт меню : ", menuRange)));
        } while (!this.exit);
    }

    public void stop() {
        this.exit = true;
    }

}
