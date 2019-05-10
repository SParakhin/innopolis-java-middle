package part01.lesson06.task02.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Утилитный класс properties
 */
public class ReadProperties {

    public static String getProperties(String prop) {
        File file = new File("src\\part01\\lesson06\\task02\\properties\\task.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String property = properties.getProperty(prop);
        return property;
    }
}