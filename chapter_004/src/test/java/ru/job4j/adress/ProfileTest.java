package ru.job4j.adress;

import org.junit.Test;
import ru.job4j.adress.*;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProfileTest {

    @Test
    public void whenGetProfilesTakeAdress() {
        Address adr1 =  new Address("York", "street1", 32, 21);
        Address adr2 =  new Address("Petushki", "street2", 12, 71);

        List<Profile> profiles = List.of(
                new Profile(adr1),
                new Profile(adr2));

        Profile profile = new Profile();
        List<Address> result = profile.collect(profiles);
        List<Address> expected = List.of(
                new Address("York", "street1", 32, 21),
                new Address("Petushki", "street2", 12, 71));
        result.forEach(System.out::println);
        assertThat(result, is(expected));
    }


}
