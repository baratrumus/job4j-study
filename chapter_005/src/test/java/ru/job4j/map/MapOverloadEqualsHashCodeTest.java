package ru.job4j.map;

import org.junit.Test;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MapOverloadEqualsHashCodeTest {

    @Test
    public void mapNotOverload() {
        MapOverloadEqualsHashCode ml = new MapOverloadEqualsHashCode();

        MapOverloadEqualsHashCode.User user1 = ml.new User("Anton", 4, new GregorianCalendar(2000, 1, 25));
        MapOverloadEqualsHashCode.User user2 = ml.new User("Anton", 4, new GregorianCalendar(2000, 1, 25));

        Map<MapOverloadEqualsHashCode.User, Integer> map = new HashMap<>();
        map.put(user1, 23);
        map.put(user2, 90);
        System.out.println(user1.equals(user2));
        System.out.println(map);

        //user23 был заменен на user 90
    }
}
