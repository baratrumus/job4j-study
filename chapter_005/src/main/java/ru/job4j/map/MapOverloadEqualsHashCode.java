package ru.job4j.map;


import java.util.Calendar;
import java.util.Objects;

/**
 * Перекрыть Equals и Hashcode.
 * При выводе из теста мы видим, что мапа считает ключи одинаковыми, при добавлении заменяет старое значение новым,
 * это видно на печати и при выводе мапы на печать есть только 1 значение.
 */

public class MapOverloadEqualsHashCode {
    public class User {
        String name;
        int children;
        Calendar birthday;

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
            MapOverloadEqualsHashCode.User user = (MapOverloadEqualsHashCode.User) o;
            return children == user.children
                    && Objects.equals(name, user.name)
                    && Objects.equals(birthday, user.birthday);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, children, birthday);
        }
    }

}
