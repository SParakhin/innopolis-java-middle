/**
 * ДЗ_8
 * Дан интерфейс
 *
 * public interface Worker {
 *
 *     void doWork();
 *
 * }
 *
 * Необходимо написать программу, выполняющую следующее:
 *
 * Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 * После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле SomeClass.java.
 * Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 * Полученный файл подгружается в программу с помощью кастомного загрузчика
 * Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
*/
package part01.lesson09.task01;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, IOException {

        getMethodFromConsole();
        customCompiler();
        useCustomClassLoader();
    }

    static void useCustomClassLoader() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class<?> c = customClassLoader.findClass("part01.lesson09.task01.out.SomeClass");
        Object ob = c.newInstance();
        Method md = c.getMethod("doWork");
        md.invoke(ob);
    }

    static void customCompiler() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null,
                new File("src/part01/lesson09/task01/out/SomeClass.java").getAbsolutePath());
        if (result == 0) {
            System.out.println("compilation done");
        }
    }

    static void getMethodFromConsole() {
        Scanner s = new Scanner(System.in);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/part01/lesson09/task01/out/SomeClass.java"))) {
            bw.write("package part01.lesson09.task01.out;\n");

            bw.write("import part01.lesson09.task01.Worker;\n");
            bw.write("public class SomeClass implements Worker{\n");
            bw.write("public void doWork() {\n");
            bw.write("System.out.print(\"\\\"Hello world !!!\\\"\");\n");

//            while (true) {
//                String line = s.nextLine();
//                if (line.equals("")) {
//                    break;
//                }
//                bw.write(line);
//            }

            bw.write("}\n");
            bw.write("}");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}