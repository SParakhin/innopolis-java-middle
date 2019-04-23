package part01.lesson02.task03.entity;

public class Person implements Comparable<Person> {

    private String name;
    private int age;
    private final Sex sex;

    public Person(String name, int age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return
                sex + "=" + age + "=" + name;
    }

    @Override
    public int compareTo(Person o) {
        int sexCompare = this.getSex().compareTo(o.getSex());
        if (sexCompare != 0) {
            return sexCompare;
        }
        int ageCompare = o.getAge() - this.getAge();
        if (ageCompare != 0) {
            return ageCompare;
        }
        return this.getName().compareTo(o.getName());
    }
}