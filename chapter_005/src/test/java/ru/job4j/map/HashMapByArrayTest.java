package ru.job4j.map;


import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.set.SimpleSet;

import java.util.Iterator;

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
    public void whenInsertToNullPositionItsOk() {
        assertThat(hm.get(1), is("ff"));
        assertThat(hm.get(2), is("jj"));
        assertThat(hm.get(3), is("oo"));
    }

    @Test
    public void whenInsertAndReplaceItsOk() {
        hm.insert(1, "uuuuuuuuuuuuuuu");
        assertThat(hm.get(1), is("uuuuuuuuuuuuuuu"));
    }

    @Test
    public void whenDeleteItsOk() {
        hm.delete(1);
        assertNull(hm.get(1));
    }

    @Test
    public void whenIterrateItsOk() {
        HashMapByArray<String, String> hm1 = new HashMapByArray<>(8);
        hm1.insert("fdfg1", "ff1");
        hm1.insert("nnyy2", "jj2");
        hm1.insert("mklml3", "oo3");
        hm1.insert("545gf4", "ff4");
        hm1.insert("poiere5", "ff5");
        hm1.insert("por8e6", "ff6");
        hm1.insert("pui7", "ff7");
        hm1.insert("ity8", "ff8");
        Iterator<String> it = hm1.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("ff1"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("ff7"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("oo3"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("ff4"));
       // assertThat(it.hasNext(), is(false));
    }


}
