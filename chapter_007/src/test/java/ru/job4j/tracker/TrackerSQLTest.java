package ru.job4j.tracker;

import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {


    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void whenAddItemThanFoundIt() throws Exception {
        try (TrackerSQL sql = new TrackerSQL()) {
            Item item = new Item("f4", "i", new Timestamp(System.currentTimeMillis()).getTime());
            sql.add(item);
            String id = item.getId();
            assertThat(sql.findById(id).getName(), is("f4"));
        }
    }

    @Test
    public void whenDeleteThenNoItem() throws Exception {
        try (TrackerSQL sql = new TrackerSQL()) {
            Item item = new Item("f4", "i", new Timestamp(System.currentTimeMillis()).getTime());
            sql.add(item);
            String id = item.getId();
            sql.delete(id);
            assertNull(sql.findById(id));
        }
    }

    @Test
    public void whenReplaceThenItemReplaced() throws Exception {
        try (TrackerSQL sql = new TrackerSQL()) {
            Item item = new Item("f4", "i", new Timestamp(System.currentTimeMillis()).getTime());
            sql.add(item);
            String id = item.getId();
            Item newItem = new Item("newIt", "i", new Timestamp(System.currentTimeMillis()).getTime());
            sql.replace(id, newItem);
            assertThat(sql.findById(id).getName(), is("newIt"));
        }
    }


}
