package Utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Settings {

    private static String token;
    private static String path = "/home/william/Cat6Bot/config.properties";

    public static String getToken() {

        Properties properties = new Properties();
        InputStream inputStream;

        try {
            inputStream = new FileInputStream(path);
            properties.load(inputStream);

            token = properties.getProperty("token");

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (token != null) {
                return token;
            }
        }
        return null;
    }
}

