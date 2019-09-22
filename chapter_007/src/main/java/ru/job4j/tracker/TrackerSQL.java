package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackerSQL  implements ITracker, AutoCloseable {
    private Connection connection;
    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    public TrackerSQL() {
        this.init();
    }

    private void createTableItems() {
        try (PreparedStatement prepStatement =
                     this.connection.prepareStatement(
                             "create table if not exists tracker_items (id serial primary key not null, name varchar(250),"
                                     + " description text, created timestamp);")
        ) {
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    //Config.class.getClassLoader().getResource(app.properties).getFile();
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            if (in != null) {
                config.load(in);
            }
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            createTableItems();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement pStatement = connection.prepareStatement("insert into tracker_items(name, description, created) values (?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            pStatement.setString(1, item.getName());
            pStatement.setString(2, item.getDecs());
            pStatement.setTimestamp(3, new Timestamp(item.getTime()));
            pStatement.executeUpdate();
            ResultSet generatedKeys = pStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                item.setId(String.valueOf(generatedKeys.getInt(1)));
                System.out.println(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean res = false;
        int index;
        if (id == null || item == null) {
            return res;
        }
        try (PreparedStatement pStatement = connection.prepareStatement(
                "update tracker_items set name = ?, description = ?, created = ? where id = ?")) {
            pStatement.setString(1, item.getName());
            pStatement.setString(2, item.getDecs());
            pStatement.setTimestamp(3, new Timestamp(item.getTime()));
            pStatement.setInt(4, Integer.parseInt(id));
            if (pStatement.executeUpdate() > 0) {
                res = true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        boolean res = false;
        if (id == null) {
            return res;
        }
        try (PreparedStatement pStatement = connection.prepareStatement("delete from tracker_items where id = ?;")) {
            pStatement.setInt(1, Integer.parseInt(id));
            if (pStatement.executeUpdate() > 0) {
                res = true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return res;
    }

    @Override
    public List<Item> findAll() {
        List<Item> retList = new ArrayList<>();
        try (PreparedStatement pStatement = connection.prepareStatement("select * from tracker_items;")) {
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                Item tmpItem = new Item(rs.getString("name"), rs.getString("description"),
                        rs.getTimestamp("created").getTime());
                tmpItem.setId(String.valueOf(rs.getInt("id")));
                retList.add(tmpItem);
            }
            rs.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return retList;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> retList = new ArrayList<>();
        try (PreparedStatement pStatement = connection.prepareStatement("select * from tracker_items where name = ?;")) {
            pStatement.setString(1, key);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                Item tmpItem = new Item(rs.getString("name"), rs.getString("description"),
                        rs.getTimestamp("created").getTime());
                tmpItem.setId(String.valueOf(rs.getInt("id")));
                retList.add(tmpItem);
            }
            rs.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return retList;
    }

    @Override
    public Item findById(String id) {
        Item ret = null;
        try (PreparedStatement pStatement = connection.prepareStatement("select * from tracker_items where id = ?;")) {
            pStatement.setInt(1, Integer.parseInt(id));
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                ret = new Item(rs.getString("name"), rs.getString("description"),
                        rs.getTimestamp("created").getTime());
                ret.setId(String.valueOf(rs.getInt("id")));
            }
            rs.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return ret;
    }

    @Override
    public void close() throws Exception {
        if (this.connection != null) {
            this.connection.close();
        }
    }

    public static void main(String[] args) {
        TrackerSQL sql = new TrackerSQL();
        Item item = new Item("f4", "i", new Timestamp(System.currentTimeMillis()).getTime());
        //sql.add(item);
        item = sql.findById("3");
        System.out.println(item.getName());

    }
}
