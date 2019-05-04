package part01.lesson04.task02;

public class Main {

    public static void main(String[] args) {

        ObjectBox objectBox = new ObjectBox();

        objectBox.addAll(1);
        objectBox.addAll("строка");
        objectBox.addAll(3);
        System.out.println("====== Содержимое коллекции объектов");
        objectBox.dump();
        objectBox.deleteObject(1);
        System.out.println("\n");
        System.out.println("======Содержимое коллекции объектов после удаления элемента");
        objectBox.dump();
    }
}