/**
 * Задание 2. Создать генератор текстовых файлов, работающий по следующим правилам:
 * Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
 * Слово состоит из 1<=n2<=15 латинских букв
 * Слова разделены одним пробелом
 * Предложение начинается с заглавной буквы
 * Предложение заканчивается (.|!|?)+" "
 * Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений. В конце абзаца стоит разрыв строки и перенос каретки.
 * Есть массив слов 1<=n4<=1000.
 * Есть вероятность probability вхождения одного из слов этого массива в следующее предложение (1/probability).
 * Необходимо написать метод getFiles(String path, int n, int size, String[] words, int probability),
 * который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
 */

package part01.lesson06.task02;

import java.io.*;
import java.util.*;

public class Main {

    static final int MAX_LENGHT_SENTENSE = new Random().nextInt(15);
    static final int LENGHT_ARRAY = 1000;
    static final int MAX_LENGHT_PARAGRAPH = new Random().nextInt(20);

    public static void main(String[] args) {
        String[] words = getWordsArray();
        getFiles("src\\part01\\lesson06\\task02\\", 3, 3000, words);
    }

    /**
     * Формирование массива слов
     *
     * @return Массив слов
     */
    static String[] getWordsArray() {
        String[] words = new String[LENGHT_ARRAY];
        for (int i = 0; i < words.length; i++) {
            words[i] = generatedWordsToArray();
        }
        return words;
    }

    /**
     * Формирование массива абзацев
     *
     * @param sentense Массив предложений
     * @return Массив абзацев
     */
    static String[] getParagraphsArray(String[] sentense) {
        String[] paragraph = new String[LENGHT_ARRAY];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paragraph.length; i++) {
            int count = 0;
            for (int j = 0; j < sentense.length; j++) {
                if (sentense[j] != null) {
                    paragraph[i] = sb.append(sentense[new Random().nextInt(paragraph.length)] + " " + "\n").toString();
                    count++;
                    if (count == MAX_LENGHT_PARAGRAPH) {
                        sb.delete(0, sb.length());
                        break;
                    }
                }
            }
        }
        return paragraph;
    }

    /**
     * Формирование массива знаков пунктуации
     *
     * @return символ знака пунктуации для окончания предложения
     */
    static String getPunctuationMark() {
        String[] punctuation = {"!", "?", "."};
        return punctuation[new Random().nextInt(punctuation.length)];
    }

    /**
     * Генерация знаков препинания для предложения
     *
     * @return
     */
    static String getComma() {
        String[] comma = {",", " "};
        return comma[new Random().nextInt(comma.length)];
    }

    /**
     * Формирование массива предложений
     *
     * @param words Массив слов
     * @return Массив предложений
     */
    static String[] getSentensesArray(String[] words) {
        String[] sentense = new String[LENGHT_ARRAY];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentense.length; i++) {
            int count = 0;
            String temp = "";
            for (int j = 0; j < words.length; j++) {
                if (words[j] != null) {
                    temp = sb.append(words[new Random().nextInt(sentense.length)] + getComma()).toString();
                    count++;
                    if (count == MAX_LENGHT_SENTENSE) {
                        String capitalized = temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase();
                        sentense[i] = capitalized.trim() + getPunctuationMark();
                        if (sentense[i].endsWith(",?") || sentense[i].endsWith(",!") || sentense[i].endsWith(",.")) {
                            StringBuilder sb1 = new StringBuilder(sentense[i]);
                            sentense[i] = sb1.deleteCharAt(sb.length() - 1).toString();
                        }
                        sb.delete(0, sb.length());
                        sb.delete(0, sb.length());
                        break;
                    }
                }
            }
        }
        return sentense;
    }

    /**
     * Генерация слов для массива
     *
     * @return Строка из случайного набора символов
     */

    static String generatedWordsToArray() {
        String symbols = "abcdefghqwerty";
        StringBuilder randString = new StringBuilder();
        int count = (int) (Math.random() * 15);
        for (int i = 0; i < count; i++)
            randString.append(symbols.charAt((int) (Math.random() * symbols.length())));
        if (randString.toString().length() > 0) {
            return randString.toString();
        }
        return symbols;
    }

    /**
     * Метод создания/записи файла
     *
     * @param path  Каталог для сохранения файлов. По умолчанию - каталог текущего пакета.
     * @param n     Количество файлов для записи
     * @param size  Размер файлов для записи
     * @param words Массив слов
     */
    static void getFiles(String path, int n, int size, String[] words) {
        for (int i = 1; i <= n; i++) {
            words = getWordsArray();
            String[] sentense = getSentensesArray(words);
            String[] paragraph = getParagraphsArray(sentense);
            String fileName = path + i + ".txt";
            File file = new File(fileName);
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                for (int k = 0; k < paragraph.length; k++) {
                    while (file.length() < size) {
                        fileOutputStream.write(paragraph[new Random().nextInt(paragraph.length)].getBytes());
                        fileOutputStream.write("\n".getBytes());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("==Записан файл. Размер:" + file.length());
        }
    }
}