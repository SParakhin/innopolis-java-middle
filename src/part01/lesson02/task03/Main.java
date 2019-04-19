/**
 * Задание 3. Дан массив объектов Person. Класс Person характеризуется полями age (возраст, целое число 0-100),
 * sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN), name (имя - строка).
 * Создать два класса, методы которых будут реализовывать сортировку объектов.
 * Предусмотреть единый интерфейс для классов сортировки.
 * Реализовать два различных метода сортировки этого массива по правилам:
 * первые идут мужчины
 * выше в списке тот, кто более старший
 * имена сортируются по алфавиту
 * Программа должна вывести на экран отсортированный список и время работы каждого алгоритма сортировки.
 * Предусмотреть генерацию исходного массива (10000 элементов и более).
 * Если имена людей и возраст совпадают, выбрасывать в программе пользовательское исключение.
 */
package part01.lesson02.task03;

import java.util.*;

import static part01.lesson02.task03.SortPersonThen.*;

public class Main {

    public static void main(String[] args) throws MyException {
/**
 Генерация исхдного массива
 */
        Person[] person = new Person[10000];
        for (int i = 0; i < person.length; i++) {
            Random random = new Random();
            int age = random.nextInt(100);
            person[i] = new Person(generatedNameForArray(), age, randomEnum(Sex.class));
        }

        /**
         Два метода сортировки с замером производительности
         */
        long startSortingTimeFirstAlgoritm = System.currentTimeMillis();
        Arrays.sort(person, new SortPerson());
        long finishSortingTimeFirstAlgoritm = System.currentTimeMillis();
        long timeSortingFirstAlgoritm = finishSortingTimeFirstAlgoritm - startSortingTimeFirstAlgoritm;

        long startSortingTimeSecondAlgoritm = System.currentTimeMillis();
        Arrays.sort(person, new SortPersonThen().thenComparing(new SortBySex().thenComparing(new SortByName())));
        long finishSortingTimeSecondAlgoritm = System.currentTimeMillis();
        long timeSortingSecondAlgoritm = finishSortingTimeSecondAlgoritm - startSortingTimeSecondAlgoritm;

        for (Person p : person) {
            System.out.println(p);
        }

        System.out.println("==============================");
        System.out.println("Time sorting first algoritm" + " " + timeSortingFirstAlgoritm);
        System.out.println("Time sotring second algoritm" + " " + timeSortingSecondAlgoritm);

/**
 Если имена людей и возраст совпадают, выбрасывать в программе пользовательское исключение
 */
        for (int i = 0; i < person.length; i++) {
            for (int j = 0; j < person.length; j++) {
                if (j == i) continue;
                if (person[j].getName().equals(person[i].getName()) && person[j].getAge() == person[i].getAge())
                    throw new MyException("Matches found for name and age");
                break;
            }
        }
    }

    /**
     Метод для автозаполнения массива значениями из enum Sex
     */
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = new Random().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    /**
     Метод для генерации случайной строки для заполнения в массиве поля name
     */
    public static String generatedNameForArray() {
        String symbols = "abc";
        StringBuilder randString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < symbols.length(); i++)
            randString.append(symbols.charAt(random.nextInt(symbols.length())));
        return randString.toString();
    }

    static class MyException extends Exception {
        public MyException(String error) {
            super(error);
        }
    }
}

