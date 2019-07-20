package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * Перекрыть только hashCode
 * При переопределении только hashCode,  при добавлении элемента в мапу хеш будет одинаковый, это видно при выводе
 * из теста, но equals разный. В методе добавления пары в мапу проверяются отдельно хеш и equals,
 * поэтому если не переопределены оба, мапа будет считать ключи разнымии при выводе мапы на печать будут выведены 2 значения.
 */

public class MapOverloadOnlyHash {
    public class User {
        private String name;
        private int children;
        private Calendar birthday;

        public User(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, children, birthday);
        }
    }
}

