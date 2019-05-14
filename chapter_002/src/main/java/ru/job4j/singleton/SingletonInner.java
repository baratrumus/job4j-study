package ru.job4j.singleton;

import ru.job4j.tracker.*;

/**
 * private static final class. Lazy loading
 * объект класса находиться в поле внутреннего класса.
 */
class SingletonInner {
    private SingletonInner() {
    }
    Tracker tracker;

    public static SingletonInner getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final SingletonInner INSTANCE = new SingletonInner();
    }
}
