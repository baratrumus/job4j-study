package ru.job4j.collections.comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
	 * В интерфейсе List есть метод сорт, действует методом слияния, требует компаратора
	 * Либо мы пишем компаратор - анонимный класс
	 * Либо объявляем class User implements Comparable<User>
	 * И реализуем метод compareTo()
	 *
	 * Если объекты равны по name, то можно сортировать по второму полю Фамилия
	 * Компаратор  compare может вернуть 0 - два объекта равны,
	 * 								     1 - объект 1 больше чем объект 2
	 *                                  -1 	объект 2 больше чем объект 1
	 *
	 *  он вызывает compareTo, здесь так же 0 - две строки одинаковы
	 *                                     >0 строка1 больше чем аргумент-строка
	 *                                     <0	cтрока1 меньше чем аргумент-строка
	 *
	 *                     //Более короткая строка  precedes	длинной, т.е меньше
	 */


public class SortList {

    static class User {
        private final String name;

        public User(String name) {
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

            User user = (User) o;

            return name != null ? name.equals(user.name) : user.name == null;
        }

        @Override
        public int hashCode()   {
            return name != null ? name.hashCode() : 0;
        }

        public String toString() {
            return "User{" + "name=\"" + name + "\"" + "}";
        }
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList<User>();
        users.addAll(Arrays.asList(
                new User("Petr"),
                new User("Zuma"),
                new User("Oleg")));

        System.out.println(users);	//при переопределенном toString печатает всю коллекцию

     
        users.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o1.name.compareTo(o2.name);
                    }
                });
        System.out.println(users);

    }
}
