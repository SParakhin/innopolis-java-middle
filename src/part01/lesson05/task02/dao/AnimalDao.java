package part01.lesson05.task02.dao;

import part01.lesson05.task02.entity.Animal;
import part01.lesson05.task02.entity.Person;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnimalDao {


    public static Set<Animal> animals = new HashSet<>();

     /**
     * Поиск животного по кличке
     *
     * @param name Кличка животного
     * @return Найденное животное
     */
    public static Animal getAnimalByName(String name) {
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
     * @param id
     * @return Найденное животное
     */
    public static Animal getAnimalById(int id) {
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
    public static Animal updateAnimal(Animal animal, String name, Person person) {
        if (animal.getId() != 0 && animal.getName() != null) {
            animal.setName(name);
            animal.setPerson(person);
        }
        return animal;
    }

    /**
     * Добавление животного в список
     * @param animal
     */
    public static void addAnimal(Animal animal) {
        if (animals.contains(animal)) {
            try {
                throw new Exception("Животное с такими параметрами существует - " + animal.toString());
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        animals.add(animal);
    }

    public static void printSortedAnimals(List<Animal> animals) {
        for (Animal a : animals) {
            System.out.println(a.getPerson().getName() + " " + a.getName() + " " + a.getWeight());
        }
    }

    public static void printAnimals() {
        for (Animal a : animals) {
            System.out.println(a.getPerson().getName() + " " + a.getName() + " " + a.getWeight());
        }
    }
}

