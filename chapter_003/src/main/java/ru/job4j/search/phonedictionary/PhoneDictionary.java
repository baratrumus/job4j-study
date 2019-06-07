package ru.job4j.search.phonedictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PhoneDictionary {

    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Телефонный справочник на базе ArrayList.
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        Iterator<Person> iter = persons.iterator();
        Person per;
        while (iter.hasNext()) {
            per = iter.next();
            if (per.getName().contains(key)
                    || per.getSurname().contains(key)
                    || per.getPhone().contains(key)
                    || per.getAddress().contains(key)) {
                result.add(per);
            }
        }
        return result;
    }
}
