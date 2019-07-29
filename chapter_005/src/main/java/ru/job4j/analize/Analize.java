package ru.job4j.analize;

import java.util.List;
import java.util.Objects;

public class Analize {

    /**
     * метод должен возвращать статистику об изменении коллекции.
     */
    public Info diff(List<Users> previous, List<Users> current) {
        Info res = new Info();
        res.changed = checkChange(previous,  current);
        res.added = checkAdd(previous,  current);
        res.deleted = checkDelete(previous,  current);

        return res;
    }



    /**
     * Сколько удалено пользователей. Удаленным считается объект из старого, который отсутствует в новом списке.
     */
    private int checkDelete(List<Users> previous, List<Users> current) {
        int res = 0;
        boolean deleted;
        for (Users prevUser : previous) {
            deleted = true;
            for (Users curUser : current) {
                if (prevUser.equals(curUser)) {
                   deleted = false;
                   break;
                }
            }
            if (deleted) {
                res++;
            }
        }
        return res;
    }

    /**
     * Сколько изменено пользователей. Изменённым считается объект в котором изменилось имя. а id осталось прежним.
     */
    private int checkChange(List<Users> previous, List<Users> current) {
        int res = 0;
        for (Users prevUser : previous) {
            for (Users curUser : current) {
                if ((prevUser.id == curUser.id) && (!prevUser.name.equals(curUser.name))) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * Сколько добавлено пользователей. Добавленным считается объект в котором id не присутствующее в старом списке.
     */
    private int checkAdd(List<Users> previous, List<Users> current) {
        int res = 0;
        boolean exist;
        for (Users curUser : current) {
            exist = false;
            for (Users prevUser : previous) {
                if (prevUser.id == curUser.id) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
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
