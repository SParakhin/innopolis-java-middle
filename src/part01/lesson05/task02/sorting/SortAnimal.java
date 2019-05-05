package part01.lesson05.task02.sorting;

import part01.lesson05.task02.entity.Animal;

import java.util.Comparator;

/**
 * Утилитный класс сортировки имя владельца-кличка питомца-вес питомца
 */

public class SortAnimal implements Comparator<Animal> {

    @Override
    public int compare(Animal o1, Animal o2) {
        if (o1!=null && o2!=null) {
            int namePersonCompare = o1.getPerson().getName().compareTo(o2.getPerson().getName());
            if (namePersonCompare != 0) {
                return namePersonCompare;
            }
            int nameAnimalCompare = o1.getName().compareTo(o2.getName());
            if (nameAnimalCompare != 0) {
                return nameAnimalCompare;
            }
        }
        return o1.getWeight() - o2.getWeight();
    }
}






