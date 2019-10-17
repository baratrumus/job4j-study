
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class StoreSQL implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger(StoreSQL.class.getName());

    private final Config config = new Config();
    private Connection connection;

    public StoreSQL(Connection connection) {
        this.connection = connection;
        this.init();
    }

    private void init() {
        try (PreparedStatement prepStatement =
                     this.connection.prepareStatement(
                             "create table if not exists vacancies (id serial primary key not null, vacancy_name varchar(250),"
                                     + " description text, actual_date timestamp, link text);")
        ) {
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public Vacancy add(Vacancy vacancy) throws SQLException {
        String sql = "INSERT INTO vacancies(vacancy_name, description, actual_date, link) values (?, ?, ?, ?);";
        try (PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (checkForNoDuplicate(vacancy)) {
                st.setString(1, vacancy.getName());
                st.setString(2, vacancy.getDescription());
                st.setTimestamp(3, Timestamp.valueOf(vacancy.getDate()));
                st.setString(4, vacancy.getLink());
                ResultSet generatedKeys = st.getGeneratedKeys(); //получение перв ключа добавленной записи
                if (generatedKeys.next()) {
                    LOGGER.warn("query inserted...");
                    System.out.println(generatedKeys.getInt(1));
                }
                st.executeUpdate();
                this.connection.commit();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return vacancy;
    }

    public LocalDateTime getLastParseDate() {
        LocalDateTime lastDate = LocalDateTime.now().minusYears(1);
        try (PreparedStatement pst = this.connection
                .prepareStatement("SELECT max(actual_date) as date FROM vacancies")) {
            ResultSet rs = pst.executeQuery();
            if (rs.next() && rs.getTimestamp("date") != null) {
                LOGGER.info(rs.getTimestamp("date"));
                lastDate = rs.getTimestamp("date").toLocalDateTime();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return lastDate;
    }


    public boolean checkForNoDuplicate(Vacancy vacancy) {
        String vacancyName = vacancy.getName();
        try (PreparedStatement Checker = this.connection
                .prepareStatement("SELECT * FROM vacancies WHERE vacancy_name = ?")) {
            Checker.setString(1, vacancyName);
            ResultSet rs = Checker.executeQuery();
            if (!rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    public List<Vacancy> getAll() {
        List<Vacancy> result = new ArrayList<>();
        try (PreparedStatement prepStatement = this.connection.prepareStatement("select * from vacancies;")) {
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                result.add(new Vacancy(
                                rs.getString("vacancy_name"),
                                rs.getString("description"),
                                rs.getTimestamp("actual_date").toLocalDateTime(),
                                rs.getString("link")
                        )
                );
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    public void addListOfVacancies(List<Vacancy> vacancies) {
        String sql = "INSERT INTO vacancies(vacancy_name, description, actual_date, link) values (?,?,?,?);";
        try (PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            for (Vacancy vacancy : vacancies) {
                prepStatement.setString(1, vacancy.getName());
                prepStatement.setString(2, vacancy.getDescription());
                prepStatement.setTimestamp(3, Timestamp.valueOf(vacancy.getDate()));
                prepStatement.setString(4, vacancy.getLink());
                prepStatement.addBatch();
            }
            prepStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
