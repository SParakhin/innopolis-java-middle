/**
 * ДЗ_4
 * Разработать программу – картотеку домашних животных. У каждого животного есть уникальный идентификационный номер, кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 * <p>
 * Реализовать:
 * <p>
 * метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 * поиск животного по его кличке (поиск должен быть эффективным)
 * изменение данных животного по его идентификатору
 * вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 * Тестовый класс
 */

package part01.lesson05.task02;

import part01.lesson05.task02.dao.AnimalDao;
import part01.lesson05.task02.entity.Animal;
import part01.lesson05.task02.entity.Person;
import part01.lesson05.task02.entity.Sex;
import part01.lesson05.task02.sorting.SortAnimal;

import java.util.*;

import static part01.lesson05.task02.dao.AnimalDao.*;
import static part01.lesson05.task02.dao.AnimalDao.animals;

public class Main {

    public static void main(String[] args) {

        Animal animal = new Animal(1, "Васька", 6);
        animal.setPerson(new Person("Сергей", 44, Sex.МУЖСКОЙ));
        Animal animal2 = new Animal(2, "Пушок", 3);
        animal2.setPerson(new Person("Иван", 32, Sex.МУЖСКОЙ));
        Animal animal3 = new Animal(3, "Мурка", 12);
        animal3.setPerson(new Person("Алина", 32, Sex.ЖЕНСКИЙ));
        Animal animal4 = new Animal(4, "Черныш", 11);
        animal4.setPerson(new Person("Надежда", 28, Sex.ЖЕНСКИЙ));

        addAnimal(animal);
        addAnimal(animal);
        addAnimal(animal2);
        addAnimal(animal3);
        addAnimal(animal4);

        System.out.println("=======Первоначальный список");
        printAnimals();

        System.out.println("=========================");
        System.out.println("Найдено совпадение по имени " + getAnimalByName("Васька"));
        System.out.println("Найдено совпадение по id " + getAnimalById(2));
        System.out.println("У питомца с id 1 изменено имя и данные владельца " + updateAnimal(getAnimalById(1), "Мурзик", new Person("Наташа", 22, Sex.ЖЕНСКИЙ)));

        List<Animal> sortedAnimalSet = new ArrayList<>(animals);
        Collections.sort(sortedAnimalSet, new SortAnimal());
        System.out.println("=====Отсортированный список - владелец, кличка,вес питомца");
        printSortedAnimals(sortedAnimalSet);
    }
}