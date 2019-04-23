/**
 * Задание 1. Написать программу ”Hello, World!”. В ходе выполнения программы она должна выбросить исключение
 * и завершиться с ошибкой.
 * Смоделировав ошибку «NullPointerException»
 * Смоделировав ошибку «ArrayIndexOutOfBoundsException»
 * Вызвав свой вариант ошибки через оператор throw
 */

package part01.lesson02.task01;

public class HelloWorld {

    public static void main(String[] args) throws MyException {

        String name = null;
        try {
            System.out.println(name.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int[] array = new int[10];
        try {
            int x = array[11];
        } catch (Exception e) {
            e.printStackTrace();
        }

        int a = 5;
        if (a / 5 > 0) {
            throw new MyException("My exception");
        }
    }
}