package ru.job4j.xmljdbc;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties values = new Properties();

    Config() {
        this.init();
    }

    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app.propertiesSQLLite")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String getKey(String key) {
        return this.values.getProperty(key);
    }
}
