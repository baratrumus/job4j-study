package ru.job4j.sorting;

public class User implements Comparable<User> {
    private final String name;
    private final int age;
    static int i = 0;

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
        int result = Integer.compare(this.age, o.age);
        if (result == 0) {
            result = this.name.compareTo(o.name);
        }
        i = 1;
        return result;
    }
}
