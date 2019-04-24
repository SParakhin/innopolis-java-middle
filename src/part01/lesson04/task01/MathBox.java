/**
 * Задание 1. Написать класс MathBox, реализующий следующий функционал:
 * Конструктор на вход получает массив Number. Элементы не могут повторяться. Элементы массива внутри объекта раскладываются в подходящую коллекцию (выбрать самостоятельно).
 * Существует метод summator, возвращающий сумму всех элементов коллекции.
 * Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель, являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
 * Необходимо правильно переопределить методы toString, hashCode, equals, чтобы можно было использовать MathBox для вывода данных на экран и хранение объектов этого класса в коллекциях (например, hashMap). Выполнение контракта обязательно!
 * Создать метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.
 */
package part01.lesson04.task01;

import java.util.*;

/**
 * Класс-обертка для массива типа Number
 * @param <T> Все наследники Number
 */
public class MathBox<T extends Number> {

    private T[] numbers;

    public MathBox(T[] numbers) {
        this.numbers = numbers;
    }

    Set<Number> store = new HashSet<>();

    /**
     * Метод для преобразования массива чисел в список
     *
     * @param numbers Входной массив
     * @param <T> Наследники от Number
     */
    <T extends Number> void addAll(T[] numbers) {
        for (T t : numbers) {
            store.add(t);
        }
    }

    /**
     * Метод для нахождения суммы всех элементов списка чисел
     *
     * @param store Список элементов для суммирования
     * @return сумма элементов списка
     */
    public double summator(Set<T> store) {
        double sum = 0;
        for (T t : store) {
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

    public Set<? super Number> splitter(Set<T> store, long div) {
        Set<? super Number> newStore = new HashSet<>();
        for (T t : store) {
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
    public Set<? extends Number> deleteInteger(Set<T> store, Integer del) {
        Set<Integer> tmp = new HashSet<>();
        for (T t : store) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox<?> mathBox = (MathBox<?>) o;
        return Arrays.equals(numbers, mathBox.numbers) &&
                Objects.equals(store, mathBox.store);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(store);
        result = 31 * result + Arrays.hashCode(numbers);
        return result;
    }
}