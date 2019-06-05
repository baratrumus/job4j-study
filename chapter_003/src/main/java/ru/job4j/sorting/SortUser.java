package ru.job4j.sorting;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {
    /**
     * @return TreeSet пользователей, отсортированных по возрасту в порядке возрастания.
     */
    public static TreeSet<User> sort (List<User> luser) {
        TreeSet<User> ret = new TreeSet<User>();
        for (User user : luser) {
            ret.add(user);
        }
        return ret;
    }
}
