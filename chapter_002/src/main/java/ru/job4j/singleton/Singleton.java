package ru.job4j.singleton;


/**
 * static field. Lazy loading.
 * Для того, чтобы закрыть возможность создавать экземпляр класса,
 * нам нужно явно создать конструтор по умолчанию и присвоить ему модификатор private.
 */
class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}


/**
 * вариант singleton на перечислениях
 */
enum  SingletonEnum {
    INSTANCE
}


/**
 * Вариант static final field. Eager loading, при инициализации класса
 */
class Singleton1 {
    private static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}

/**
 * private static final class. Lazy loading
 * объект класса находиться в поле внутреннего класса.
 */
class Singleton2 {
    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final Singleton2 INSTANCE = new Singleton2();
    }
}



