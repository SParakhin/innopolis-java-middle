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

package part01.lesson05.task03;

import part01.lesson05.task03.entity.Animal;
import part01.lesson05.task03.entity.Person;
import part01.lesson05.task03.entity.Sex;
import part01.lesson05.task03.util.Sequence;

import static part01.lesson05.task03.dao.AnimalDao.*;

public class Main {

    public static void main(String[] args) {

        Sequence sequence = new Sequence();

        Animal animal = new Animal(sequence.nextValue(), "Васька", 3);
        Animal animal1 = new Animal(sequence.nextValue(), "Пушок", 5);
        Animal animal2 = new Animal(sequence.nextValue(), "Пушок", 3);
        Animal animal3 = new Animal(sequence.nextValue(), "Пушок", 4);
        Animal animal4 = new Animal(sequence.nextValue(), "Барсик", 5);
        Animal animal5 = new Animal(sequence.nextValue(), "Черныш", 7);

        Person person = new Person("Сергей", 43, Sex.MAN);
        Person person1 = new Person("Дима", 30, Sex.MAN);
        Person person2 = new Person("Света", 30, Sex.WOMAN);
        Person person3 = new Person("Оля", 35, Sex.WOMAN);
        Person person4 = new Person("Оксана", 20, Sex.WOMAN);
        Person person5 = new Person("Сергей", 20, Sex.MAN);

        animal.setPerson(person);
        animal1.setPerson(person1);
        animal2.setPerson(person2);
        animal3.setPerson(person3);
        animal4.setPerson(person4);
        animal5.setPerson(person5);

        animalMap.put(animal.getId(), animal);
        animalMap.put(animal1.getId(), animal1);
        animalMap.put(animal2.getId(), animal2);
        animalMap.put(animal3.getId(), animal3);
        animalMap.put(animal4.getId(), animal4);
        animalMap.put(animal5.getId(), animal5);

        System.out.println("==Попытка добавить животное с существующими параметрами");
        addAnimal(animal.getId(), animal);
        System.out.println(animalMap);
        System.out.println("==Поиск животного по id " + getAnimalById(5));
        System.out.println("=====Поиск Черныш до изменения параметров");
        getAnimalByName("Черныш");
        System.out.println("==Изменение параметров животного (вес) " + updateAnimal(5, 15));
        System.out.println("==Поиск Черныш после изменения параметров");
        getAnimalByName("Черныш");
        System.out.println("==Поиск совпадений по имени");
        getAnimalByName("Пушок");
        getAnimalByName("Васька");
        System.out.println("==Вывод в отсортированном порядке - Хозяин, кличка животного, вес");
        sortedAnimalsMap();
    }
}