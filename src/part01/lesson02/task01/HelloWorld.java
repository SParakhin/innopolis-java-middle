/**Задание 1. Написать программу ”Hello, World!”. В ходе выполнения программы она должна выбросить исключение
 и завершиться с ошибкой.
 Смоделировав ошибку «NullPointerException»
 Смоделировав ошибку «ArrayIndexOutOfBoundsException»
 Вызвав свой вариант ошибки через оператор throw
 */
package part01.lesson02.task01;

public class HelloWorld {

    private String name;

    private int[] array = new int[10];

    public String getName() {
        return name;
    }

    public int[] getArray() {
        return array;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public static void main(String[] args) throws MyException {
        HelloWorld helloWorld = new HelloWorld();
        if (helloWorld.getName() == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < 100; i++) {
            if (i > helloWorld.getArray().length) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }

        int a = 5;
        if (a / 5 > 0) {
            throw new MyException("My exception");
        }
    }
}

class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}