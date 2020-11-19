package ru.job4j.map;

import org.junit.Test;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MapOverloadOnlyEqualsTest {

    @Test
    public void mapNotOverload() {
        MapOverloadOnlyEquals ml = new MapOverloadOnlyEquals();

        MapOverloadOnlyEquals.User user1 = ml.new User("Anton", 4, new GregorianCalendar(2000, 1, 25));
        MapOverloadOnlyEquals.User user2 = ml.new User("Anton", 4, new GregorianCalendar(2000, 1, 25));

        Map<MapOverloadOnlyEquals.User, Integer> map = new HashMap<>();
        map.put(user1, 23);
        map.put(user2, 23);
        System.out.println(user1.equals(user2));
        System.out.println(map);

        // юзера равны по икуалс но добавлены как разные ключи
    }
}
