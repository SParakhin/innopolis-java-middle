package part01.lesson08.task02.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Класс для получения переменных из файла свойств
 */
public class ReadProperties {

    public static Properties getProperties() {
        final String PATH_TO_PROPERTIES = "src/part01/lesson08/task02-no-commit/properties/task.properties";
        Properties prop = new Properties();
        try (InputStream is = Files.newInputStream(Paths.get(PATH_TO_PROPERTIES))) {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}