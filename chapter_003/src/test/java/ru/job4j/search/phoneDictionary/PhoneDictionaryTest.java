package ru.job4j.search.phoneDictionary;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
public void whenFindByName() {
    PhoneDictionary phones = new PhoneDictionary();
    phones.add(
            new Person("Ivan", "Petrov", "334455", "Brest")
    );
    phones.add(
            new Person("Oleg", "Krotov", "334455", "Brest")
    );
    phones.add(
            new Person("Vasia", "Olgin", "334455", "Brest")
    );
    List<Person> persons = phones.find("Ol");
    Iterator<Person> iter = persons.iterator();
    assertThat(iter.next().getSurname(), is("Krotov"));
    assertThat(iter.next().getSurname(), is("Olgin"));
}
}
