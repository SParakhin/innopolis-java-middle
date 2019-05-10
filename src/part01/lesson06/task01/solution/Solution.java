package part01.lesson06.task01.solution;

import java.io.*;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static part01.lesson06.task01.util.ReadProperties.getProperties;

public class Solution {

    private static final String INPUT_FILE = getProperties("inputFile");
    private static final String OUTPUT_TXT = getProperties("outputFile");

    /**
     * Метод записи в файл отсортированного списка слов
     *
     * @param tokens
     */
    public static void writeTextToFile(Set<String> tokens) {
        try (BufferedWriter ostream = new BufferedWriter(new FileWriter(OUTPUT_TXT))) {
            Iterator<String> iterator = tokens.iterator();
            while (iterator.hasNext()) {
                ostream.write(iterator.next());
                ostream.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, читающий текстовый файл
     *
     * @return
     */

    public static Set<String> readTextFromFile() {
        Set<String> tokens = new TreeSet<>();
        try (BufferedReader istream = new BufferedReader(new FileReader(INPUT_FILE))) {

            while (true) {
                if (!istream.ready()) break;
                for (String token : istream.readLine().split("\\P{L}+")) {
                    if (token.trim().length() > 0) {
                        tokens.add(token.toLowerCase());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokens;
    }
}