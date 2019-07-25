package ru.job4j.map;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.set.SimpleSet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class HashMapByArrayTest {

    private HashMapByArray<Integer, String> hm;

    @Before
    public void beforeTest() {
        hm = new HashMapByArray<>(8);
        hm.insert(1, "ff");
        hm.insert(2, "jj");
        hm.insert(3, "oo");
        hm.insert(4, "ff");
        hm.insert(5, "ff");
        hm.insert(6, "ff");
        hm.insert(7, "ff");
        hm.insert(8, "ff");
    }

    @Test
    public void whenGrowSizeAndReassignBucketsItsOk() {
        assertThat(hm.getSize(), is(16));
    }


    @Test
    public void whenInsertItsOk() {
        assertThat(hm.get(1), is("ff"));
        assertThat(hm.get(2), is("jj"));
        assertThat(hm.get(3), is("oo"));
    }

    @Test
    public void whenDeleteItsOk() {
        hm.delete(1);
        assertNull(hm.get(1));

    }


}
