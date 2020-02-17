package data;


import models.Order;
import models.Seat;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class CinemaDB implements Db {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final CinemaDB INSTANCE = new CinemaDB();
    private static final Logger LOG = LoggerFactory.getLogger(CinemaDB.class);

    public CinemaDB() {
       init();
       createTableItems();
    }

    public static CinemaDB getInstance() {
        return INSTANCE;
    }

    private Connection getConnect() {
        try {
            return SOURCE.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void init() {
        try (InputStream in = CinemaDB.class.getClassLoader().getResourceAsStream("app.properties")) {
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

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }


    private void createTableItems() {
        int[] result = null;
        Connection connection = getConnect();
        String makeHall = "create table if not exists seats (id serial primary key not null, row int, columnC int);";

        String makeOrders = "create table if not exists  orders (id serial primary key not null, phone varchar(250),"
                    + " fullname  varchar(250), row int, columnc int);";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(makeHall);
            ps.executeUpdate();
            connection.commit();
            ps = connection.prepareStatement(makeOrders);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException sqle) {
                LOG.error(sqle.getMessage(), sqle);
                sqle.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }  catch (SQLException sqle) {
                LOG.error(sqle.getMessage(), sqle);
                sqle.printStackTrace();
            }
        }
    }


    @Override
    public Order addNewOrder(Order order) {
        String addOrder = "INSERT INTO orders (phone, fullname, row, columnc) values(?, ?, ?, ?);";
        Connection connection = getConnect();
        try {
            PreparedStatement ps = connection.prepareStatement(addOrder);
            connection.setAutoCommit(false);
            ps.setString(1, order.getPhone());
            ps.setString(2, order.getFullName());
            ps.setInt(3, Integer.parseInt(order.getRow()));
            ps.setInt(4, Integer.parseInt(order.getColumn()));
            ps.executeUpdate();
            connection.commit();
        }  catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            try {
                connection.rollback();
            } catch (SQLException sqle) {
                LOG.error(sqle.getMessage(), sqle);
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }  catch (SQLException sqle) {
                LOG.error(sqle.getMessage(), sqle);
            }
        }
        addPlace(order);
        return order;
    }

    /**
     * Таблица seats нужна опционально, работа идет через orders
     */
    private void addPlace(Order order) {
        String addHalls = "INSERT INTO seats (row, columnc) values(?,?);";
        Connection connection = getConnect();
        try {
            PreparedStatement ps = connection.prepareStatement(addHalls);
            connection.setAutoCommit(false);
            ps.setInt(1, Integer.parseInt(order.getRow()));
            ps.setInt(2, Integer.parseInt(order.getColumn()));
            ps.executeUpdate();
            connection.commit();
        }  catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            try {
                connection.rollback();
            } catch (SQLException sqle) {
                LOG.error(sqle.getMessage(), sqle);
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }  catch (SQLException sqle) {
                LOG.error(sqle.getMessage(), sqle);
            }
        }
    }

    @Override
    public List<Order> getOrders() {
        ArrayList result = new ArrayList<Order>();
        String addHalls = "select * from orders;";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(addHalls)
        ) {
            connection.setAutoCommit(false);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Order(rs.getString("phone"),
                                     rs.getString("fullname"),
                                     String.valueOf(rs.getInt("row")),
                                     String.valueOf(rs.getString("columnc"))));
            }
            rs.close();
            connection.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }
}
