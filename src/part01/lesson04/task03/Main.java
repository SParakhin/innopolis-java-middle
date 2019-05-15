package part01.lesson04.task03;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Number[] array = {1, 2, 3, 4, 4, 5, 6};
        MathBox mathBox = new MathBox(array);

        System.out.println("Массив преобразован в список уникальных элементов - " + mathBox.store);
        System.out.println("Сумма всех элементов списка - " + mathBox.summator());
        System.out.println("Список после деления на делитель 3 - " + mathBox.splitter(3));
        System.out.println("Список после удаления элемента Integer 4 - " + mathBox.deleteInteger(4));

        System.out.println("Добавляем в MathBox новый элемент  - 123");
        mathBox.addAll(123);
//        mathBox.addAll("попытка добавить строку в MathBox");
        System.out.println("Список после добавления новых элементов в коллекцию MathBox: ");
        mathBox.dump();

        ObjectBox objectBox = new ObjectBox();
        objectBox.addAll("строка");
        objectBox.addAll(45689);
        objectBox.dump();
    }
}