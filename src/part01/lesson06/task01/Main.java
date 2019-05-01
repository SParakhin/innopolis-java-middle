/**
 * Написать программу, читающую текстовый файл.
 * Программа должна составлять отсортированный по алфавиту список слов,
 * найденных в файле и сохранять его в файл-результат.
 * Найденные слова не должны повторяться, регистр не должен учитываться.
 * Одно слово в разных падежах – это разные слова.
 * <p>
 * Входной текст для тестирования в файле input.txt
 * Выходные данные в файле output.txt
 * Перед запуском очистить содержимое файла output.txt в текущем пакете
 */

package part01.lesson06.task01;

import java.io.*;
import java.util.*;

public class Main {

    static final String inputFile = "src\\part01\\lesson06\\task01\\input.txt";
    static final String outputFile = "src\\part01\\lesson06\\task01\\output.txt";

    public static void main(String[] args) {
        Set<String> tokens = readTextFormFile();
        writeTextToFile(tokens);
    }

    /**
     * Метод записи в файл отсортированного списка слов
     *
     * @param tokens
     */
    private static void writeTextToFile(Set<String> tokens) {

        BufferedWriter ostream = null;
        try {
            ostream = new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Iterator<String> iterator = tokens.iterator();
            while (iterator.hasNext()) {
                ostream.write(iterator.next());
                ostream.write("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ostream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ostream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, читающий текстовый файл
     *
     * @return
     */

    private static Set<String> readTextFormFile() {
        BufferedReader istream = null;
        try {
            istream = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Set<String> tokens = new TreeSet<>();
        while (true) {
            try {
                if (!istream.ready()) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
//                for (String token : istream.readLine().split("\\s+(\\s|,|:|!|.|\\.)\\s")) {
//                for (String token : istream.readLine().split("[ !\"\\#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]+")) {
                for (String token : istream.readLine().split("\\P{L}+")) {
//                for (String token : istream.readLine().split("\\s*(\\s|,|!|\\.)\\s*")) {
                    if (token.trim().length() > 0) {
                        tokens.add(token.toLowerCase());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            istream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokens;
    }
}