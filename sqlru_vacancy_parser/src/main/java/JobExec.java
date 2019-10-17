
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
    private SiteParser sqlRuParser = new SiteParser(this.connectDatabase());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        this.LOGGER.warn("Parsing process started");
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

