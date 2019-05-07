package part01.lesson04.task03;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ObjectBox<T> {

    Set<T> list = new HashSet<>();

    void addAll(T object) {
        list.add(object);
    }

    Set<T> deleteObject(T object) {
        list.remove(object);
        return list;
    }

    void dump() {
        System.out.print(list.toString());
    }

    @Override
    public String toString() {
        return "," + list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox<?> objectBox = (ObjectBox<?>) o;
        return Objects.equals(list, objectBox.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}





