package part01.lesson12.task01;

/**
 * ДЗ_11
 * Задание 1.     Необходимо создать программу, которая продемонстрирует утечку памяти в Java.
 * При этом объекты должны не только создаваться, но и периодически частично удаляться,
 * чтобы GC имел возможность очищать часть памяти. Через некоторое время программа должна завершиться
 * с ошибкой OutOfMemoryError c пометкой Java Heap Space.
 *
 * Задание 2.     Доработать программу так, чтобы ошибка OutOfMemoryError возникала в Metaspace /Permanent Generation
 */

public class TestOOM {

    public static void main(String[] args) throws Exception {
        new HeapOOM().oomByMem();
    }

    public static void showMemoryInfo() {
        System.out.print("\nTotal memory : " + Runtime.getRuntime().totalMemory());
        System.out.print(" Free : " + Runtime.getRuntime().freeMemory());
        System.out.print(" Max : " + Runtime.getRuntime().maxMemory());
    }
}