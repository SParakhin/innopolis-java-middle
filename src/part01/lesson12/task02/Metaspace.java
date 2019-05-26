package part01.lesson12.task02;

/**
 * Задание 2.     Доработать программу так, чтобы ошибка OutOfMemoryError возникала в Metaspace /Permanent Generation
 * ДЛя запуска программы необходим импорт библиотеки Javassist
 * -XX:MaxMetaspaceSize=512m
 */
public class Metaspace {

    static javassist.ClassPool cp = javassist.ClassPool.getDefault();

    public static void main(String[] args) throws Exception{
        for (int i = 0; ; i++) {
            Class c = cp.makeClass("part01.lesson12.task02.Generated" + i).toClass();
        }
    }
}