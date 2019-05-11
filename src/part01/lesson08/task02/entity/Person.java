package part01.lesson08.task02.entity;

import java.io.Serializable;

public class Person implements Serializable {

    private String name;
    private int age;
    private double height;
    private boolean married;
    private Animal animal;

    public Person(String name, int age, double height, boolean married) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.married = married;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", height=").append(height);
        sb.append(", married=").append(married);
        sb.append(", animal=").append(animal);
        sb.append('}');
        return sb.toString();
    }
}