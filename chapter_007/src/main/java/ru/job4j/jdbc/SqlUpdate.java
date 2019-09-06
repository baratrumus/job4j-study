package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class SqlUpdate {
    private static final Logger LOG = LoggerFactory.getLogger(SqlSelect.class);


    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/products";
        //String url = "jdbc:postgresql://127.0.0.1:55291/products";
        String username = "postgres";
        String password = "dfghjk";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement("update product set name = ? where id = ?");
            st.setString(1, "fffffffffffff");
            st.setInt(2, 7);
            st.executeQuery();
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
