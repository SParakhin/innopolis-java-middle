/**
 * Задание 3. Доработать классы MathBox и ObjectBox таким образом, чтобы MathBox был наследником ObjectBox.
 * Необходимо сделать такую связь, правильно распределить поля и методы.
 * Функциональность в целом должна сохраниться.
 * При попытке положить Object в MathBox должно создаваться исключение.
 */

package part01.lesson04.task03;

import java.util.*;

class MathBox extends ObjectBox {

    private Number[] numbers;

    public MathBox(Number[] numbers) {
        this.numbers = numbers;
    }

    Set<Number> store = new HashSet<>();

    /**
     * Метод для преобразования массива в коллекцию
     * @param numbers Массив чисел
     */

    public void addAll(Number[] numbers) {
        for (Number t : numbers) {
            try {
                if (numbers instanceof Number[]) {
                    store.add(t);
                } else {
                    throw new Exception("Невозможно добавить элемент.Введите число");
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        super.addAll(numbers);
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

    Set splitter(Set<Number> store, long div) {
        Set<? super Number> newStore = new HashSet<>();
        for (Number t : store) {
            newStore.add(t.longValue() / div);
        }
        return newStore;
    }

    @Override
    public Set<? extends Object> deleteObject(Set<? extends Object> list, Object object) {
        try {
            if (!(object instanceof Number)) {
                throw new Exception("Невозможно удалить элемент.Введите число");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return super.deleteObject(list, object);
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "numbers=" + Arrays.toString(numbers) +
                ", store=" + store +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MathBox mathBox = (MathBox) o;
        return Arrays.equals(numbers, mathBox.numbers) &&
                Objects.equals(store, mathBox.store);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), store);
        result = 31 * result + Arrays.hashCode(numbers);
        return result;
    }
}