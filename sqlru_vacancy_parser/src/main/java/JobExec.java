
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JobExec implements Job {
    private static final Logger LOGGER = LogManager.getLogger(RunParsing.class);
    private Config config = new Config();
    protected SiteParser sqlRuParser;
    private StoreSQL storeSQL = new StoreSQL(this.connectDatabase());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        this.LOGGER.warn("Parsing process started");
        sqlRuParser = new SiteParser(this.connectDatabase(), storeSQL);
        this.sqlRuParser.parsing();
        this.LOGGER.warn("Parsing process stopped");
    }

    public Connection connectDatabase() {
        try {
            return DriverManager.getConnection(
                    this.config.get("url"),
                    this.config.get("username"),
                    this.config.get("password")
            );
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}

