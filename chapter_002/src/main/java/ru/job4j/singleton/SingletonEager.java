package ru.job4j.singleton;

import ru.job4j.tracker.*;

/**
 * Вариант static final field. Eager loading, при инициализации класса
 */
class SingletonEager {
    private static final SingletonEager INSTANCE = new SingletonEager();
    Tracker tracker;

    private SingletonEager() {
    }

    public static SingletonEager getInstance() {
        return INSTANCE;
    }
}
