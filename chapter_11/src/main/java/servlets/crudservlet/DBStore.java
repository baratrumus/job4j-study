package servlets.crudservlet;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class DBStore implements Store<User>  {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    public DbStore() {
        SOURCE.setUrl("...");
        SOURCE.setUsername("...");
        SOURCE.setPassword("...");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public User add(User model) {
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.prepareStatement("...")
        ) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(User model) {

    }

    @Override
    public String delete(String id) {
        return null;
    }

    @Override
    public Map<Integer, User> findAll() {
        return null;
    }

    @Override
    public String findById(String id) {
        return null;
    }
}
