package com.openclassrooms.game;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Properties props;

    private static void loadProps() {

        try {
            String propFileName = "config.properties";
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName);
            props = new Properties();
            props.load(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String value) {
        if (props == null)
            loadProps();

        return props.getProperty(value);
    }
}