package part01.lesson02.task03.sorting;

import part01.lesson02.task03.entity.Person;

import java.util.Comparator;

public class SortPerson implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        int sexCompare = o1.getSex().compareTo(o2.getSex());
        if (sexCompare != 0) {
            return sexCompare;
        }
        int ageCompare = o2.getAge() - o1.getAge();
        if (ageCompare != 0) {
            return ageCompare;
        }
        return o1.getName().compareTo(o2.getName());
    }
}





