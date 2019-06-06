package ru.job4j.list.userconvert;


import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class UserConvert {
    /**
     * принимает в себя список пользователей и конвертирует его в Map
     * с ключом Integer id и соответствующим ему User.
     * @param list
     * @return
     */

    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        ListIterator<User> literator = list.listIterator();
        int i = 1;
        while (literator.hasNext()) {
            User user = literator.next();
            result.put(user.getId(), user);
        }
        return result;
    }
}
