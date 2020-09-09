package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyProvider {

    private static Properties propsStore = new Properties();

    private PropertyProvider() {
    }

    static {
        read();
    }

    public static String getProperty(String propertyName) {
        return propsStore.getProperty(propertyName);
    }

    private static void read() {
        loadProperties(propsStore, "config/config.properties");
        propsStore.putAll(System.getProperties());
    }

    private static void loadProperties(Properties props, String path) {
        try (InputStream is = PropertyProvider.class.getClassLoader().getResourceAsStream(path)) {
            props.load(is);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
