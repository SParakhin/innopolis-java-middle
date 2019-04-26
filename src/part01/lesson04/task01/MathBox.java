/**
 * Задание 1. Написать класс MathBox, реализующий следующий функционал:
 * <p>
 * Конструктор на вход получает массив Number. Элементы не могут повторяться. Элементы массива внутри объекта раскладываются в подходящую коллекцию (выбрать самостоятельно).
 * Существует метод summator, возвращающий сумму всех элементов коллекции.
 * Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель, являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
 * Необходимо правильно переопределить методы toString, hashCode, equals, чтобы можно было использовать MathBox для вывода данных на экран и хранение объектов этого класса в коллекциях (например, hashMap). Выполнение контракта обязательно!
 * Создать метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.
 */

package part01.lesson04.task01;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MathBox {

    private Number[] numbers;

    public MathBox(Number[] numbers) {
        this.numbers = numbers;
    }

    Set<Number> store = new HashSet<>();

    /**
     * Метод для преобразования массива чисел в список
     *
     * @param numbers Входной массив
     */
    void addAll(Number[] numbers) {
        for (Number t : numbers) {
            store.add(t);
        }
    }

    /**
     * Метод для нахождения суммы всех элементов списка чисел
     *
     * @param store Список элементов для суммирования
     * @return сумма элементов списка
     */
    double summator(Set<Number> store) {
        double sum = 0;
        for (Number t : store) {
            sum += t.doubleValue();
        }
        return sum;
    }

    /**
     * Метод для деления всех элементов спика на делитель
     *
     * @param store список элементов для деления
     * @param div   делитель
     * @return новый список элементов после деления
     */

    public Set<Number> splitter(Set<Number> store, long div) {
        Set<Number> newStore = new HashSet<>();
        for (Number t : store) {
            newStore.add(t.longValue() / div);
        }
        return newStore;
    }

    /**
     * Метод для удаления из списка совпадающего значения типа Integer
     *
     * @param store Список для удаления элементов
     * @param del   Значение для удаления
     * @return Новый список без удаленного элемента
     */
    public Set<Number> deleteInteger(Set<Number> store, Integer del) {
        Set<Integer> tmp = new HashSet<>();
        for (Number t : store) {
            if (t == del) {
                tmp.add((Integer) t);
            }
        }
        store.removeAll(tmp);
        return store;
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "numbers=" + Arrays.toString(numbers) +
                '}';
    }
}