package part01.lesson09.task01.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import static part01.lesson09.task01.util.ReadProperties.getProperties;

public class CreateFileForCompile {

    private static Properties properties = getProperties();
    private static final String JAVA_FILE = properties.getProperty("javaFile");

    public static void getMethodFromConsole() {
        Scanner s = new Scanner(System.in);
        File file = new File(JAVA_FILE);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("package part01.lesson09.task01.out;\n");
            bw.write("import part01.lesson09.task01.interfaces.Worker;\n");
            bw.write("public class SomeClass implements Worker{\n");
            bw.write("public void doWork() {\n");
            while (true) {
                String line = s.nextLine();
                if (line.equals("")) {
                    break;
                }
                bw.write(line);
            }
            bw.write("}\n");
            bw.write("}");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
