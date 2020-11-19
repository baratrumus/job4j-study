package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MapOverloadOnlyHashTest {

    @Test
    public void mapNotOverload() {
        MapOverloadOnlyHash ml = new MapOverloadOnlyHash();

        MapOverloadOnlyHash.User user1 = ml.new User("Anton", 4, new GregorianCalendar(2000, 1, 25));
        MapOverloadOnlyHash.User user2 = ml.new User("Anton", 4, new GregorianCalendar(2000, 1, 25));

        Map<MapOverloadOnlyHash.User, Integer> map = new HashMap<>();
        map.put(user1, 23);
        map.put(user2, 23);
        System.out.println(user1.equals(user2));
        System.out.println(map);

        // юзера добавлены как разные ключи, тк хеш переопр и он проверяется,
        // но дальше сверяется икуалс а его нет те объекты разные
    }
}
