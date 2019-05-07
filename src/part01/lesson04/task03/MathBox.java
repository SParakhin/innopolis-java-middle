/**
 * Задание 3. Доработать классы MathBox и ObjectBox таким образом, чтобы MathBox был наследником ObjectBox.
 * Необходимо сделать такую связь, правильно распределить поля и методы.
 * Функциональность в целом должна сохраниться.
 * При попытке положить Object в MathBox должно создаваться исключение.
 */

package part01.lesson04.task03;

import java.util.*;

class MathBox extends ObjectBox<Number> {

    Set<Number> store = super.list;

    public MathBox(Number[] numbers) {
        this.store.addAll(Arrays.asList(numbers));
    }

    /**
     * Метод для добавления элемента в коллекцию.
     *
     * @param object Объект типа Number
     */
    @Override
    void addAll(Number object) {
        super.addAll(object);
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

    @Override
    void dump() {
        System.out.println(store.toString());
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "store=" + store +
                '}';
    }

    Set<Number> deleteInteger(Integer del) {
        this.store.remove(del);
        return this.store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MathBox mathBox = (MathBox) o;
        return Objects.equals(store, mathBox.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), store);
    }
}