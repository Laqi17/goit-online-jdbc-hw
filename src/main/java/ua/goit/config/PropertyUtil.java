package ua.goit.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    private Properties properties;

    public PropertyUtil() {
        load();
    }

    public String getHostName() {
        return properties.getProperty("database.hostname");
    }
    public String getSchema() {
        return properties.getProperty("database.schema");
    }

    public Integer getPort() {
        return Integer.parseInt(properties.getProperty("database.port"));
    }

    public String getUser() {
        return properties.getProperty("database.user");
    }

    public String getPassword() {
        return properties.getProperty("database.password");
    }

    private void load() {
        this.properties = new Properties();
        try (InputStream is = ClassLoader.getSystemResourceAsStream("application.properties")) {
            properties.load(is);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
