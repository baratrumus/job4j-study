package ru.job4j.sorting;

public class User implements Comparable<User> {
    private final String name;
    private final int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String toString() {
        return "User{" + "name=\"" + this.name + "\"" + "age=\"" + this.age + "}";
    }

    @Override
    public int compareTo(User o) {
        return Integer.valueOf(this.age).compareTo(Integer.valueOf(o.age));
    }

}
