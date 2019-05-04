package part01.lesson05.task01.entity;

import java.util.Objects;

/**
 * Класс-сущность питомец
 */
public class Animal {

    private int id;
    private String name;
    private int weight;
    private Person person;

    public Animal(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public Animal() {
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", person=" + person +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id &&
                weight == animal.weight &&
                Objects.equals(name, animal.name) &&
                Objects.equals(person, animal.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight, person);
    }

    public Integer getId(int id) {
        return id;
    }

    public String getName(String name) {
        return name;
    }
}
