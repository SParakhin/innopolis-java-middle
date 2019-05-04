package part01.lesson04.task03;

public class Main {
    public static void main(String[] args) {
        Number[] array = {1, 2, 3, 4, 4, 5, 6};
        MathBox mathBox = new MathBox(array);
        System.out.println("Массив преобразован в список уникальных элементов - " + mathBox.store);
        System.out.println("Сумма всех элементов списка - " + mathBox.summator());
        System.out.println("Список после деления на делитель 3 - " + mathBox.splitter(3));
        System.out.println("Список после удаления элемента Integer 4 - " + mathBox.deleteInteger(4));
        mathBox.addAll("Строка");
        System.out.println("Список после попытки добавления новых элементов в коллекцию MathBox: ");
        mathBox.dump();
    }
}