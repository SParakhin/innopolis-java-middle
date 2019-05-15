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

import java.util.*;
import static part01.lesson06.task01.solution.Solution.readTextFromFile;
import static part01.lesson06.task01.solution.Solution.writeTextToFile;

public class Main {

    public static void main(String[] args) {

        Set<String> tokens = readTextFromFile();
        writeTextToFile(tokens);
    }
}