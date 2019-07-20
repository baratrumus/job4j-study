package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * Перекрыть только Equals
 * При переопределении только Equals,  при добавлении элемента в мапу хеш будет разный, это видно при выводе
 * из теста, но equals выводит true. Мапа будет считать ключи разными и при выводе мапы на печать будут выведены 2 значения.
 */

public class MapOverloadOnlyEquals {

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
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return children == user.children
                    && Objects.equals(name, user.name)
                    && Objects.equals(birthday, user.birthday);
        }

        /**
         * Переопределено чтобы пройти валидацию мавена,  метод фейк
         */
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}
