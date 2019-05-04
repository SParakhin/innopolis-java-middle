package part01.lesson04.task03;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class ObjectBox {

    Set<Object> list = new HashSet<>();

    void addAll(Object object) {
        this.list.add(object);
    }

    Set<? extends Object> deleteObject(Object object) {
        Iterator iterator = this.list.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            if (o.equals(object) || o == object) {
                iterator.remove();
            }
        }
        return this.list;
    }

    void dump() {
        for (Object t : this.list) {
            System.out.println(t + " ");
        }
    }

    @Override
    public String toString() {
        return "ObjectBox{" +
                "list=" + list +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox objectBox = (ObjectBox) o;
        return Objects.equals(list, objectBox.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}





