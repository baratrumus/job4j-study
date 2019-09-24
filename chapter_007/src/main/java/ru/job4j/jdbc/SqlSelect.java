package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;


public class SqlSelect {
    private static final Logger LOG = LoggerFactory.getLogger(SqlSelect.class);


    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/products";
        String username = "postgres";
        String password = "dfghjk";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement("SELECT * FROM product as p where p.id in (?, ?)");
            st.setInt(1, 2);
            st.setInt(2, 4);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.print(String.format("%s %s \n", rs.getString("name"), rs.getTimestamp("expired_date")));
            }
            rs.close();
            st.close();
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
