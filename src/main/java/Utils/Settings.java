package Utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Settings {

    private static String token;
    private static String apiKey;
    public static String path = "";

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

    public static String getApiKey() {
        Properties properties = new Properties();
        InputStream inputStream;

        try {
            inputStream = new FileInputStream(path);
            properties.load(inputStream);

            apiKey = properties.getProperty("api-key");

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (apiKey != null) {
                return apiKey;
            }
        }
        return null;
    }
}

