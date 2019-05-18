package part01.lesson09.task01.loader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

import static part01.lesson09.task01.util.ReadProperties.getProperties;

public class CustomClassLoader extends ClassLoader {

    private static Properties properties = getProperties();
    private static final String CLASS_FILE = properties.getProperty("classFile");
    private static final String JAVA_FILE = properties.getProperty("javaFile");
    private static final String CLASS_NAME = properties.getProperty("className");


    @Override
    public Class findClass(String name) {
        byte[] classFile = new byte[0];
        try {
            classFile = Files.readAllBytes(Paths.get(CLASS_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, classFile, 0, classFile.length);
    }

    public static void useCustomClassLoader() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class<?> c = customClassLoader.findClass(CLASS_NAME);
        Object ob = c.newInstance();
        Method md = c.getMethod("doWork");
        md.invoke(ob);
    }

    public static void customCompiler() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null,
                String.valueOf(new File(JAVA_FILE)));
    }

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
