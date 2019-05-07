/**
 * Задание 1. Написать класс MathBox, реализующий следующий функционал:
 * <p>
 * Конструктор на вход получает массив Number. Элементы не могут повторяться. Элементы массива внутри объекта раскладываются
 * в подходящую коллекцию (выбрать самостоятельно).
 * Существует метод summator, возвращающий сумму всех элементов коллекции.
 * Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель, являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
 * Необходимо правильно переопределить методы toString, hashCode, equals, чтобы можно было использовать MathBox для вывода данных на экран и хранение объектов этого класса в коллекциях (например, hashMap). Выполнение контракта обязательно!
 * Создать метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.
 */

package part01.lesson04.task01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MathBox {

    Set<Number> store = new HashSet<>();

    public MathBox(Number[] numbers) {
        this.store.addAll(Arrays.asList(numbers));
    }

    /**
     * Метод для нахождения суммы всех элементов списка чисел
     *
     * @return сумма элементов коллекции
     */
    double summator() {
        double sum = 0;
        for (Number t : this.store) {
            sum += t.doubleValue();
        }
        return sum;
    }

    /**
     * Метод для деления всех элементов спика на делитель
     *
     * @param div делитель
     * @return новый список элементов после деления
     */
    Set<Number> splitter(long div) {
        Set<Number> newStore = new HashSet<>();
        for (Number t : this.store) {
            newStore.add(t.longValue() / div);
        }
        return newStore;
    }

    /**
     * Метод для удаления из списка совпадающего значения типа Integer
     *
     * @param del Значение для удаления
     * @return Новый список без удаленного элемента
     */
    Set<Number> deleteInteger(Integer del) {
        this.store.remove(del);
        return this.store;
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "store=" + store +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return Objects.equals(store, mathBox.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(store);
    }
}