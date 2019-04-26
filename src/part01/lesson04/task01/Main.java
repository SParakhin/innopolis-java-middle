package part01.lesson04.task01;

import part01.lesson04.task01.MathBox;

public class Main {

    public static void main(String[] args) {
        Number[] array = {1, 2, 3, 4, 4, 5, 6, 6.2};

        MathBox mathBox = new MathBox(array);
        mathBox.addAll(array);
        System.out.println("Массив преобразован в список уникальных элементов - " + mathBox.store);
        System.out.println("Сумма всех элементов списка - " + mathBox.summator(mathBox.store));
        System.out.println("Список после деления на делитель 3 - " + mathBox.splitter(mathBox.store, 3));
        System.out.println("Список после удаления совпадающего элемента 4 - " + mathBox.deleteInteger(mathBox.store, 4));
    }
}
