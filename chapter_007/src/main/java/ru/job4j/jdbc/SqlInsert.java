package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SqlInsert {

    private static final Logger LOG = LoggerFactory.getLogger(SqlInsert.class);


    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/products";
        //String url = "jdbc:postgresql://127.0.0.1:55291/products";
        String username = "postgres";
        String password = "dfghjk";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement(
                    "insert into product(name, type_id, expired_date, price) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "TEST28");
            st.setInt(2, 2);
            st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            st.setInt(4, 55);
            st.executeUpdate();
            LOG.warn("load query...");
            ResultSet generatedKeys = st.getGeneratedKeys(); //получение перв ключа добавленной записи
            if (generatedKeys.next()) {
                System.out.println(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }
}
