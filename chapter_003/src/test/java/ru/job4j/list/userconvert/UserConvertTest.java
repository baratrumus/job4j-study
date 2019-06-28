package ru.job4j.list.userconvert;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    UserConvert userConvert = new UserConvert();

    @Test
    public void whenConvert3Its3() {

        List<User> list = new ArrayList<>();
        User bob = new User(22, "Bob", "Oslo");
        User pit = new User(33, "Pit", "Magadan");
        User lera = new User(66, "Lera", "Chelabinsk");
        list.add(bob);
        list.add(pit);
        list.add(lera);


        HashMap<Integer, User> result = userConvert.process(list);

        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(22, bob);
        expect.put(33, pit);
        expect.put(66, lera);


        assertThat(result, is(expect));

    }
}

