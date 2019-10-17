import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties VALUES = new Properties();
    private static final Logger LOGGER = LogManager.getLogger(RunParsing.class.getName());

    public Config() {
        this.init();
    }

    /**
     *  properties from args
     */
    public Config(String properties) {
        try (FileInputStream fin = new FileInputStream(properties)) {
            this.VALUES.load(fin);
            LOGGER.info("load properties from file");
        } catch (Exception e) {
            LOGGER.error("file with properties not found");
            System.exit(1);
        }
    }

    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
            VALUES.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.VALUES.getProperty(key);
    }
}
