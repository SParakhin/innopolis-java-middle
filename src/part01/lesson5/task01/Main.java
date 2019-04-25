/**
 * ДЗ_4
 * Разработать программу – картотеку домашних животных. У каждого животного есть уникальный идентификационный номер, кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 *
 * Реализовать:
 *
 * метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 * поиск животного по его кличке (поиск должен быть эффективным)
 * изменение данных животного по его идентификатору
 * вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 * Тестовый класс
 */

package part01.lesson5.task01;

import part01.lesson5.task01.entity.Animal;
import part01.lesson5.task01.entity.Person;
import part01.lesson5.task01.sorting.SortAnimal;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Set<Animal> animals = new HashSet<>();
        Animal animal = new Animal(1, "Васька", 6);
        animal.setPerson(new Person("Сергей", 44, "мужской"));
        Animal animal2 = new Animal(2, "Пушок", 3);
        animal2.setPerson(new Person("Иван", 32, "мужской"));
        Animal animal3 = new Animal(3, "Мурка", 12);
        animal3.setPerson(new Person("Алина", 32, "женский"));
        Animal animal4 = new Animal(4, "Черныш", 11);
        animal4.setPerson(new Person("Надежда", 28, "женский"));

        addAnimal(animals, animal);
        addAnimal(animals, animal);
        addAnimal(animals, animal2);
        addAnimal(animals, animal3);
        addAnimal(animals, animal4);

        System.out.println("=======Первоначальный список");
        for (Animal a : animals) {
            System.out.println(a);
        }

        System.out.println("=========================");
        System.out.println("Найдено совпадение по имени " + getAnimalByName(animals, "Васька"));
        System.out.println("Найдено совпадение по id " + getAnimalById(animals, 2));
        System.out.println("У питомца с id 1 изменено имя и данные владельца " + updateAnimal(getAnimalById(animals, 1), "Мурзик", new Person("Наташа", 22, "женский")));

        List<Animal> sortedAnimalSet = new ArrayList<>(animals);
        Collections.sort(sortedAnimalSet, new SortAnimal());
        System.out.println("=====Отсортированный список - владелец, кличка,вес питомца");
        printSetAnimal(sortedAnimalSet);
    }

    /**
     * Поиск животного по кличке
     *
     * @param animals Список животных для поиска
     * @param name    Имя искомого животного
     * @return Найденное животное
     */
    static Animal getAnimalByName(Set<Animal> animals, String name) {
        Animal animal = new Animal();
        for (Animal a : animals) {
            if (a.getName().equals(name)) {
                animal = a;
            }
        }
        return animal;
    }

    /**
     * Поиск животного по id
     *
     * @param animals Списко животных для поиска
     * @param id      Уникальный идентификационный номер животного
     * @return
     */
    static Animal getAnimalById(Set<Animal> animals, int id) {
        Animal animal = new Animal();
        for (Animal a : animals) {
            if (a.getId() == id && a.getId() != 0) {
                animal = a;
            }
        }
        return animal;
    }

    /**
     * Метод для изменения данных животного по его id
     *
     * @param animal Сущность животного получается через метод getAnimalById
     * @param name   Параметр для установки нового имени
     * @param person Параметр для установки нового владельца
     * @return
     */
    static Animal updateAnimal(Animal animal, String name, Person person) {
        if (animal.getId() != 0 && animal.getName() != null) {
            animal.setName(name);
            animal.setPerson(person);
        }
        return animal;
    }

    /**
     * Доавление животного в список
     *
     * @param animals Список для добавления
     * @param animal  Животное для добавления
     */
    static void addAnimal(Set<Animal> animals, Animal animal) {
        if (animals.contains(animal)) {
            try {
                throw new Exception("Животное с такими параметрами существует - " + animal.toString());
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        animals.add(animal);
    }

    static void printSetAnimal(List<Animal> animals) {
        for (Animal a : animals) {
            System.out.println(a.getPerson().getName() + " " + a.getName() + " " + a.getWeight());
        }
    }
}