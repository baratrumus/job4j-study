package ru.job4j.singleton;

import ru.job4j.tracker.*;

/**
 * static field. Lazy loading.
 * Для того, чтобы закрыть возможность создавать экземпляр класса,
 * нам нужно явно создать конструтор по умолчанию и присвоить ему модификатор private.
 */
class Singleton {
    private static Singleton instance;
    Tracker tracker;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}










