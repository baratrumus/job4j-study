package ru.job4j.xmljdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * После запуска приложение:
 * Если таблица entry в БД отсутствует, то создает ее.
 * вставляет в таблицу entry n записей со значениями 1..N. Если в таблице
 * находились записи, то они удаляются перед вставкой.
 */
public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;
    private static final Logger LOG = LoggerFactory.getLogger(StoreSQL.class);


    public StoreSQL(Config config) {
        this.config = config;
        this.init();
    }

    /**
     * Создается новая БД по параметрам из app.propertiesSQLLite
     * @return
     */
    private boolean init() {
        String url = config.getKey("url");
        try {
            this.connect = DriverManager.getConnection(url);
            if (this.connect != null) {
                DatabaseMetaData meta = connect.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                this.createNewTable();
            }
        } catch (SQLException e) {
            try {
                //при неудачном коннекте или ошибке операции отменяет посл. транзакцию
                this.connect.rollback();
            } catch (SQLException sqle) {
                LOG.error(sqle.getMessage(), sqle);
            }
            LOG.error(e.getMessage(), e);
        }
        return this.connect != null;
    }



    private void createNewTable() {
        String sqlDrop = "DROP TABLE if EXISTS entry;";
        String sqlCreate = "CREATE TABLE IF NOT EXISTS entry (number integer)";
        try (Statement st = this.connect.createStatement()) {
            this.connect.setAutoCommit(false);
            st.execute(sqlDrop);
            st.execute(sqlCreate);
            this.connect.commit();
        } catch (SQLException e) {
            try {
                //при неудачном коннекте или ошибке операции отменяет посл. транзакцию
                //При использовании режима autocommit=false нужно в блоке catch откатывать транзакцию
                this.connect.rollback();
            } catch (SQLException sqle) {
                LOG.error(sqle.getMessage(), sqle);
            }
            LOG.error(e.getMessage(), e);
        }
    }


    public void generate(int size) {
        String sql = "INSERT INTO entry(number) VALUES(?)";
        try (PreparedStatement prepStatement = this.connect.prepareStatement(sql)) {
            for (int num = 0; num < size; num++) {
                prepStatement.setInt(1, num);
                //prepStatement.executeUpdate();
                prepStatement.addBatch();
            }
            prepStatement.executeBatch();
            this.connect.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return лист Entry
     */
    public List<Entry> load() {
        List<Entry> result = new ArrayList<>();
        String sql = "SELECT number FROM entry;";
        try (PreparedStatement preparedStatement  = this.connect.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(new Entry(rs.getInt("number")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }

    public static void main(String[] args) {
        StoreSQL storeSQL = new StoreSQL(new Config());
        storeSQL.generate(100);
        System.out.println(storeSQL.load().size());
    }


}
