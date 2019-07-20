package ru.job4j.map;

import java.util.Calendar;

/**
 * Не перекрывать equals hashCode
 * При добавлении в Map ключа, что уже существует значение будет перезаписываться.
 * Но здесь в тесте, поскольку не переопределены методы equals hashCode, и одинаковые по полям объекты
 * при сравнении  через equals будут считаться разными,
 * при выводе мапы на печать будут выведены 2 значения.
 */
public class MapNotOverload {

    public class User {
        private String name;
        private int children;
        private Calendar birthday;

        public User(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }
    }

}