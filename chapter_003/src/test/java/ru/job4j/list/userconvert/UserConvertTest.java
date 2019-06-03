package ru.job4j.list.userconvert;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    UserConvert userConvert = new UserConvert();

    @Test
    public void whenConvert3Its3() {

        List<User> list = new ArrayList<User>();
        User bob = new User(22, "Bob", "Oslo");
        User pit = new User(33, "Pit", "Magadan");
        User lera = new User(22, "Lera", "Chelabinsk");
        list.add(bob);
        list.add(pit);
        list.add(lera);
        HashMap<Integer, User> result = userConvert.process(list);

        HashMap<Integer, User> expect = new HashMap<Integer, User>();
        expect.put(1, bob);
        expect.put(2, pit);
        expect.put(3, lera);

        assertThat(result, is(expect));

    }
}

//hashMap.put("/stylesheets", new ArrayList(Arrays.asList("/stylesheets", new Public())));