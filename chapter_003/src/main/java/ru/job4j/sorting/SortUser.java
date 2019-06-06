package ru.job4j.sorting;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class SortUser {
    /**
     * @return TreeSet пользователей, отсортированных по возрасту в порядке возрастания.
     */
    public static TreeSet<User> sort(List<User> luser) {
        TreeSet<User> ret = new TreeSet<>();
        ret.addAll(luser);
        return ret;
    }


    /**
     * Сортирует по длине имени. Компаратор в анонимном классе описан внутри.
     */
    public static List<User> sortNameLength(List<User> users) {
        users.sort(
                new Comparator<>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o1.getName().length() - (o2.getName().length());
                    }
                });
        return  users;
    }

    /**
     * сначала сортировка по имени в лексикографическом порядке, потом по возрасту.
     * Компаратор определен во внешнем методе
     */
    public static List<User> sortByAllFields(List<User> users) {
        users.sort(NameAndAge);
        return users;
    }


    public static Comparator<User> NameAndAge = new Comparator<>() {
        @Override
        public int compare(User u1, User u2) {
            int ret = u1.getName().compareTo(u2.getName());
            if (ret == 0) {
                ret = u1.getAge() - u2.getAge();
            }
            return ret;
        }
    };
}

