/**
 * ДЗ_7
 * Задание 1. Необходимо разработать класс, реализующий следующие методы:
 * void serialize (Object object, String file);
 * Object deSerialize(String file);
 * Методы выполняют сериализацию объекта Object в файл file и десериализацию объекта из этого файла. Обязательна сериализация и десериализация "плоских" объектов (все поля объекта - примитивы, или String).
 * Задание 2. Предусмотреть работу c любыми типами полей (полями могут быть ссылочные типы).
 * Требование: Использовать готовые реализации (Jaxb, jackson и т.д.) запрещается.
 */
package part01.lesson08.task01;

import part01.lesson08.task01.entity.Person;

import java.util.Properties;

import static part01.lesson08.task01.solution.Serialization.deSerialize;
import static part01.lesson08.task01.solution.Serialization.serialize;
import static part01.lesson08.task01.util.ReadProperties.getProperties;

public class Main {

    private static final Properties prop = getProperties();
    private static final String SERIALIZED_FILE = prop.getProperty("serializedFile");


    public static void main(String[] args) {

        Person person = new Person("Сергей", 44, 180.2, true);
        serialize(person, SERIALIZED_FILE);
        Person deSerializePerson = (Person) deSerialize(SERIALIZED_FILE);
        System.out.println(deSerializePerson);
    }
}