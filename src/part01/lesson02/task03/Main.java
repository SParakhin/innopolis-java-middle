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

import part01.lesson02.task03.entity.Person;
import part01.lesson02.task03.entity.Sex;
import part01.lesson02.task03.exception.MatchesException;
import part01.lesson02.task03.sorting.SortPerson;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws MatchesException {

        Person[] person = new Person[100];
        for (int i = 0; i < person.length; i++) {
            Random random = new Random();
            int age = random.nextInt(100);
            person[i] = new Person(generatedNameForArray(), age, randomEnum(Sex.class));
        }

        System.out.println("========Print array without sorting");
        for (Person p : person) {
            System.out.println(p);
        }

        System.out.println("========Print array with first sorting");
        firstSorting(person);
        for (Person p : person) {
            System.out.println(p);
        }

        System.out.println("========== Print array with bubble sorting");
        bubbleSort(person);
        for (Person p : person) {
            System.out.println(p);
        }

        for (int i = 0; i < person.length; i++) {
            for (int j = 0; j < person.length; j++) {
                if (j == i) continue;
                if (person[j].getName().equals(person[i].getName()) && person[j].getAge() == person[i].getAge())
                    throw new MatchesException("Matches found for name and age");
                break;
            }
        }
    }

    /**
     * Сортировка пузырьком
     *
     * @param person Массив объектов person
     */
    private static void bubbleSort(Person[] person) {
        long startSortingTimeSecondAlgoritm = System.currentTimeMillis();
        for (int j = 0; j < person.length - 1; j++) {
            if (person[j + 1].compareTo(person[j]) < 0) {
                Person temp = person[j];
                person[j] = person[j + 1];
                person[j + 1] = temp;
            }
        }
        long finishSortingTimeSecondAlgoritm = System.currentTimeMillis();
        long result = finishSortingTimeSecondAlgoritm - startSortingTimeSecondAlgoritm;
        System.out.println("========Time sorting second algoritm" + " " + result);
    }

    /**
     * Сортировка по возрасту, полу,имени. Интерфейс Comparator.
     *
     * @param person Массив объектов
     */
    private static void firstSorting(Person[] person) {
        long startSortingTimeFirstAlgoritm = System.currentTimeMillis();
        Arrays.sort(person, new SortPerson());
        long finishSortingTimeFirstAlgoritm = System.currentTimeMillis();
        long result = finishSortingTimeFirstAlgoritm - startSortingTimeFirstAlgoritm;
        System.out.println("========Time sorting first algoritm" + " " + result);
    }

    /**
     * Метод для заполнения массива значениями из класса Sex в случайном порядке
     *
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = new Random().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    /**
     * Метод для генерации случайной строки из набора символов для заполнения поля name
     *
     * @return
     */
    private static String generatedNameForArray() {
        String symbols = "abc";
        StringBuilder randString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < symbols.length(); i++)
            randString.append(symbols.charAt(random.nextInt(symbols.length())));
        return randString.toString();
    }
}