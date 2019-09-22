package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {


    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws SQLException, Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc", new Timestamp(System.currentTimeMillis()).getTime()));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }


    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void whenAddItemThanFoundIt() throws Exception {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("f4", "i", new Timestamp(System.currentTimeMillis()).getTime());
            sql.add(item);
            String id = item.getId();
            assertThat(sql.findById(id).getName(), is("f4"));
        }
    }

    @Test
    public void whenDeleteThenNoItem() throws Exception {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("f4", "i", new Timestamp(System.currentTimeMillis()).getTime());
            sql.add(item);
            String id = item.getId();
            sql.delete(id);
            assertNull(sql.findById(id));
        }
    }

    @Test
    public void whenReplaceThenItemReplaced() throws Exception {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("f4", "i", new Timestamp(System.currentTimeMillis()).getTime());
            sql.add(item);
            String id = item.getId();
            Item newItem = new Item("newIt", "i", new Timestamp(System.currentTimeMillis()).getTime());
            sql.replace(id, newItem);
            assertThat(sql.findById(id).getName(), is("newIt"));
        }
    }


}
