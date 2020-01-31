package servlets.crudservlet;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
*/

/**
 * добавим новое хранилище DBStore impl Store.
 * Класс DBStore должен реализовывать синглетон.
 * Внутри нужно сделать хранение данных в базе данных.
 * Нужно подключить пул коннектов.
 * Пул коннектов содержит подготовленные коннекты. Когда клиент заканчивает работу с connection,
 * он (connection) попадает обратно в пул и может быть использован повторно.
 */


public class DBStore implements Store<User>  {

    private static final Logger LOG = LoggerFactory.getLogger(DBStore.class);
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();

    private DBStore() {
        init();
    }

    static DBStore getInstance() {
        return INSTANCE;
    }

    private void init() {
        try (InputStream in = DBStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            if (in != null) {
                config.load(in);
            }
            SOURCE.setDriverClassName(config.getProperty("driver-class-name"));
            SOURCE.setUrl(config.getProperty("url"));
            SOURCE.setUsername(config.getProperty("username"));
            SOURCE.setPassword(config.getProperty("password"));
            SOURCE.setMinIdle(5);
            SOURCE.setMaxIdle(10);
            SOURCE.setMaxOpenPreparedStatements(100);

            createTableItems();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    private void createTableItems() {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                             "create table if not exists userServlet (id serial primary key not null, name varchar(250),"
                                     + " login varchar(250), email varchar(250), create_date timestamp);")
        ) {
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * создаем временного юзера, чтобы получить id в logic
     */
    @Override
    public AtomicInteger getNextId() {
        AtomicInteger id = new AtomicInteger(0);
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(
                "insert into userServlet(name, login, email, create_date) values (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            pStatement.setString(1, "tmp");
            pStatement.setString(2, "tmp");
            pStatement.setString(3, "tmp");
            pStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pStatement.executeUpdate();
            ResultSet generatedKeys = pStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id.set(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean add(User model) {
        boolean res = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(
                "update userServlet set name = ?, login = ?, email = ? where id = ?;")) {
            pStatement.setString(1, model.getName());
            pStatement.setString(2, model.getLogin());
            pStatement.setString(3, model.getEmail());
            pStatement.setInt(4, model.getId());
            if (pStatement.executeUpdate() > 0) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean update(int id, User model) {
        boolean res = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(
                "update userServlet set name = ?, login = ?, email = ? where id = ?;")) {
            pStatement.setString(1, model.getName());
            pStatement.setString(2, model.getLogin());
            pStatement.setString(3, model.getEmail());
            pStatement.setInt(4, id);
            if (pStatement.executeUpdate() > 0) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean delete(int id) {
        boolean res = false;
        if (id <= 0) {
            return res;
        }
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(
                "delete from userServlet where id = ?;")) {
            pStatement.setInt(1, id);
            if (pStatement.executeUpdate() > 0) {
                res = true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return res;
    }

    @Override
    public Map<Integer, User> findAll() {
        Map<Integer, User> retList = new HashMap<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("select * from userServlet;")) {
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                User user = new User(id, rs.getString("name"),
                        rs.getString("login"), rs.getString("email"),
                        "", "", new Role(1));
                user.setDate(rs.getTimestamp("create_date"));
                retList.put(id, user);
            }
            rs.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return retList;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("select * from userServlet where id = ?;")) {
            pStatement.setInt(1, id);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                user = new User(id, rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("email"), "", "", new Role(1));
                user.setDate(rs.getTimestamp("create_date"));
            }
            rs.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    public User userExists(String login, String password) {
        return null;
    }
}
