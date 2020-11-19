package ru.job4j.sorting;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Supplier;

public class SortUser {
    /**
     * @return TreeSet пользователей, отсортированных по возрасту в порядке возрастания.
     */

    public static TreeSet<User> sort(List<User> luser) {
        TreeSet<User> ret = new TreeSet<>(luser);
        return ret;
    }


    /**
     * Сортирует по длине имени. Компаратор в анонимном классе описан внутри.
     */
    public static List<User> sortNameLength(List<User> users) {
        users.sort(
                Comparator.comparingInt(o -> o.getName().length()));
        return  users;
    }

    /**
     * сначала сортировка по имени в лексикографическом порядке, потом по возрасту.
     * Компаратор определен во внешнем методе
     */
    public static List<User> sortByAllFields(List<User> users) {
        users.sort(nameAndAge);
        return users;
    }

    public static Comparator<User> nameAndAge = Comparator.comparing(User::getName).thenComparingInt(User::getAge);

}

