package ru.job4j.analize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Analize {

    /**
     * метод должен возвращать статистику об изменении коллекции.
     */
    public Info diff(List<Users> previous, List<Users> current) {
        Info res = new Info();
        Map<Integer, String> prevMap = new HashMap<Integer, String>();
        prevMap = previous.stream().collect(Collectors.toMap(Users :: getId, Users ::getName));
        Map<Integer, String> curMap = new HashMap<Integer, String>();
        curMap = current.stream().collect(Collectors.toMap(Users :: getId, Users ::getName));
        res.changed = checkChange(previous,  curMap);
        res.added = checkAdd(prevMap,  current);
        res.deleted = checkDelete(previous,  curMap);

        return res;
    }



    /**
     * Сколько удалено пользователей. Удаленным считается объект из старого, который отсутствует в новом списке.
     */
    private int checkDelete(List<Users> previous, Map<Integer, String> currentMap) {
        int res = 0;
        for (Users prevUser : previous) {
            String f = currentMap.get(prevUser.id);
            if (!Objects.equals(currentMap.get(prevUser.id), prevUser.name)) {
               res++;
            }

        }
        return res;
    }

    /**
     * Сколько изменено пользователей. Изменённым считается объект в котором изменилось имя. а id осталось прежним.
     */
    private int checkChange(List<Users> previous, Map<Integer, String> currentMap) {
        int res = 0;
        for (Users prevUser : previous) {
                if (currentMap.get(prevUser.id) != null && (!Objects.equals(currentMap.get(prevUser.id), prevUser.name))) {
                    res++;
                }

        }
        return res;
    }

    /**
     * Сколько добавлено пользователей. Добавленным считается объект в котором id не присутствующее в старом списке.
     */
    private int checkAdd(Map<Integer, String> prevMap, List<Users> current) {
        int res = 0;
        for (Users curUser : current) {
            if (prevMap.get(curUser.id) == null) {
                    res++;
                }
        }
        return res;
    }

    public static class Users {
        int id;
        String name;

        Users(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Users users = (Users) o;
            return id == users.id
                    && Objects.equals(name, users.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }


    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
