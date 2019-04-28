/**
 * Задание 2. Создать класс ObjectBox, который будет хранить коллекцию Object.
 * У класса должен быть метод addObject, добавляющий объект в коллекцию.
 * У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
 * Должен быть метод dump, выводящий содержимое коллекции в строку.
 */

package part01.lesson04.task02;

import java.util.*;

public class ObjectBox {

    private Object object;

    Set<Object> list = new HashSet<>();

    /**
     * Метод для добавления объекта в коллекцию
     * @param object
     */
    public void addAll(Object object) {
        list.add(object);
    }

    /**
     * Метод для удаления объекта из коллекции
     * @param list
     * @param object
     * @return
     */

    public Set<Object> deleteObject(Set<Object> list, Object object) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object t = iterator.next();
            if (t.equals(object) || t == object) {
                iterator.remove();
            }
        }
        return list;
    }

    /**
     * Метод для печати коллекции в строку
     * @param list
     */
    void dump(Set<Object> list) {
        for (Object o : list) {
            System.out.print(o + " ");
        }
    }

    @Override
    public String toString() {
        return "ObjectBox{" +
                "object=" + object +
                ", list=" + list +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox objectBox = (ObjectBox) o;
        return Objects.equals(object, objectBox.object) &&
                Objects.equals(list, objectBox.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(object, list);
    }
}