package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties = null;
    private static volatile ConfigReader isInstance = null;

    private ConfigReader() {
    }

    private void loadConfig() {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//config//dev.properties")) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigReader getInstance() {
        if (isInstance == null) {
            synchronized (ConfigReader.class) {
                if (isInstance == null) {
                    isInstance = new ConfigReader();
                    isInstance.loadConfig();
                }
            }
        }
        return isInstance;
    }

    public String getConfig(String key) {
        return System.getProperty(key, properties.getProperty(key));
    }

}
