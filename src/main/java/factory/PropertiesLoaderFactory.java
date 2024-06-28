package factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesLoaderFactory {
    private final Properties properties;
    private static final Logger LOGGER = Logger.getLogger(PropertiesLoaderFactory.class.getName());

    public PropertiesLoaderFactory(String filePath) {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading file properties", e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
