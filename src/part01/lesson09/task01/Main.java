/**
 * ДЗ_8
 * Дан интерфейс
 * <p>
 * public interface Worker {
 * <p>
 * void doWork();
 * <p>
 * }
 * <p>
 * Необходимо написать программу, выполняющую следующее:
 * <p>
 * Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 * После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле SomeClass.java.
 * Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 * Полученный файл подгружается в программу с помощью кастомного загрузчика
 * Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 */
package part01.lesson09.task01;

import static part01.lesson09.task01.loader.CustomClassLoader.*;
import static part01.lesson09.task01.util.CreateFileForCompile.getMethodFromConsole;

public class Main {

    public static void main(String[] args) throws Exception {

        getMethodFromConsole();
        customCompiler();
        useCustomClassLoader();
    }
}