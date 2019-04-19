package part01.lesson02.task03;

import java.util.Comparator;

public class SortPersonThen implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o2.getAge() - o1.getAge();
    }

    static class SortByName implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    static class SortBySex implements Comparator<Person>{

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getSex().compareTo(o2.getSex());
        }
    }
}
