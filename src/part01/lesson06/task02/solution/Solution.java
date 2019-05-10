package part01.lesson06.task02.solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Solution {

    private static final int LENGHT_ARRAY = 1000;

    /**
     * Формирование массива слов
     *
     * @return Массив слов
     */
    public static String[] getWordsArray() {
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
            for (int j = 0; j < sentense.length; j++) {
                if (sentense[j] != null) {
                    paragraph[i] = sb.append(sentense[new Random().nextInt(paragraph.length)] + " " + "\n").toString();
                    if (j >= new Random().nextInt(20)) {
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
        String[] comma = {", ", " "};
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
            String temp = "";
            for (int j = 0; j < words.length; j++) {
                if (words[j] != null) {
                    temp = sb.append(words[new Random().nextInt(sentense.length)] + getComma()).toString();
                    if (j >= new Random().nextInt(15)) {
                        String capitalized = temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase();
                        sentense[i] = capitalized.trim() + getPunctuationMark();
                        if (sentense[i].endsWith(",?") || sentense[i].endsWith(",!") || sentense[i].endsWith(",.")) {
                            StringBuilder sb1 = new StringBuilder(sentense[i]);
                            sentense[i] = sb1.deleteCharAt(sb.length() - 2).toString().trim();
                        }
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

    public static void getFiles(String path, int n, int size, String[] words) {
        for (int i = 1; i <= n; i++) {
            words = getWordsArray();
            String[] sentense = getSentensesArray(words);
            String[] paragraph = getParagraphsArray(sentense);
            String fileName = path + i + ".txt";
            File file = new File(fileName);
            try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                while (file.length() < size) {
                    fileOutputStream.write(paragraph[new Random().nextInt(paragraph.length)].getBytes());
                    fileOutputStream.write("\n".getBytes());
                }
                System.out.println("==Записан файл. Размер:" + file.length());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}