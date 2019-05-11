package part01.lesson08.task01.solution;

import java.io.*;

/**
 * Класс с методами сериализации/десериализации объекта.
 */
public class Serialization {

    /**
     * Метод для сериализации объекта в файл
     * @param object Объект для сериализации
     * @param file Файл для сохранения состояния объекта
     */
    public static void serialize(Object object, String file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Класс для десериализации объекта
     * @param file Файл для чтения состояния объекта
     * @return Объект с восстановленным состоянием
     */
    public static Object deSerialize(String file) {
        Object object = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            object = (Object) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}