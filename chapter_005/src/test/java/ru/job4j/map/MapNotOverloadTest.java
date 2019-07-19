package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MapNotOverloadTest {

    @Test
    public void mapNotOverload() {
        MapNotOverload ml = new MapNotOverload();

        MapNotOverload.User user1 = ml.new User("Anton", 4, new GregorianCalendar(2000, 1, 25));
        MapNotOverload.User user2 = ml.new User("Anton", 4, new GregorianCalendar(2000, 1, 25));

        Map<MapNotOverload.User, Integer> map = new HashMap<>();
        map.put(user1, 23);
        map.put(user2, 23);
        System.out.println(user1.equals(user2));
        System.out.println(map);
    }
}
