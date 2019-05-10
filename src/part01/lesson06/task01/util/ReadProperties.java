package part01.lesson06.task01.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    public static String getProperties(String prop) {
        File file = new File("src\\part01\\lesson06\\task01\\properties\\task.properties");
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