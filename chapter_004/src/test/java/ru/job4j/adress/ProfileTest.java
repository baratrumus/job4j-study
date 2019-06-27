package ru.job4j.adress;

import org.junit.Test;
import ru.job4j.adress.*;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProfileTest {

    @Test
    public void whenGetProfilesTakeAdress() {
        Address adr1 =  new Address("York", "Bublikov st.", 32, 21);
        Address adr2 =  new Address("Petushki", "Abrikosov st.", 12, 71);
        Address adr3 =  new Address("Petushki", "Abrikosov st.", 12, 71);

        List<Profile> profiles = List.of(
                new Profile(adr1),
                new Profile(adr2),
                new Profile(adr3));

        Profile profile = new Profile();
        List<Address> result = profile.collect(profiles);
        List<Address> expected = List.of(
                new Address("Petushki", "Abrikosov st.", 12, 71),
                new Address("York", "Bublikov st.", 32, 21));
        result.forEach(System.out::println);
        assertThat(result, is(expected));
    }


}
