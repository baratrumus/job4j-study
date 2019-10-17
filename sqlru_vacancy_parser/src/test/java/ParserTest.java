
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.ConnectionRollback;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class ParserTest {
    private static final Logger LOG = LogManager.getLogger(ParserTest.class);
    private LocalDateTime actualDate = LocalDateTime.of(2019, Month.OCTOBER, 15, 0, 0);
    private Vacancy javaVacancy;

    public Connection init() {
        try (InputStream in = StoreSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
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

    @Before
    public void beforeTest() {
        this.javaVacancy = new Vacancy("Jatf", "development", this.actualDate, "www.sql.ru");
    }

    @Test
    public void addVacancy() {
        try (StoreSQL storeSQL = new StoreSQL(ConnectionRollback.create(init()))) {
            Vacancy result = storeSQL.add(this.javaVacancy);
            assertThat(result, is(javaVacancy));
        } catch (SQLException e) {
            this.LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void getAll() {
        try (StoreSQL storeSQL = new StoreSQL(ConnectionRollback.create(init()))) {
            List<Vacancy> result = storeSQL.getAll();
            assertNotEquals(result.size(), is(0));
        } catch (SQLException e) {
            this.LOG.error(e.getMessage(), e);
        }
    }

}
