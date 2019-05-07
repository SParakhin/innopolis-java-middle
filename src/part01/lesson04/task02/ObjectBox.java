/**
 * Задание 2. Создать класс ObjectBox, который будет хранить коллекцию Object.
 * У класса должен быть метод addObject, добавляющий объект в коллекцию.
 * У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
 * Должен быть метод dump, выводящий содержимое коллекции в строку.
 */

package part01.lesson04.task02;

import java.util.*;

public class ObjectBox {

    Set<Object> list = new HashSet<>();

    /**
     * Метод для добавления объекта в коллекцию
     *
     * @param object
     */
    void addAll(Object object) {
        this.list.add(object);
    }

    /**
     * Метод для удаления объекта из коллекции
     * * @param object Объект для удаления из коллекции
     *
     * @return Коллекция объектов после удаления элемента
     */

    Set<Object> deleteObject(Object object) {
        Iterator iterator = this.list.iterator();
        while (iterator.hasNext()) {
            Object t = iterator.next();
            if (t.equals(object) || t == object) {
                iterator.remove();
            }
        }
        return this.list;
    }

    /**
     * Метод для печати коллекции в строку
     */

    void dump() {
        System.out.print(list.toString());
    }

    @Override
    public String toString() {
        return "list=" + list;
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