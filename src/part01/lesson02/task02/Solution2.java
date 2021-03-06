/**
 * Задание 2.
 * Составить программу, генерирующую N случайных чисел.
 * Для каждого числа k вычислить квадратный корень q.
 * Если квадрат целой части q числа равен k, то вывести это число на экран.
 * Предусмотреть что первоначальные числа могут быть отрицательные, в этом случае генерировать исключение.
 */
package part01.lesson02.task02;

import static java.lang.Math.*;

public class Solution2 {
    public static void main(String[] args) {

        int[] array = new int[100];
        for (int k = 0; k < array.length; k++) {
            array[k] = (int) (Math.random() * 2000 - 1000);
            try {
                if (array[k] < 0) {
                    throw new Exception("Отрицательное число, корень не извлекаем" + " " + array[k]);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            if (array[k] >= 0) {
                int q = (int) sqrt(array[k]);
                if (array[k] == q * q) {
                    System.out.println("=======Найдено совпадение" + " " + q + "*" + q + "=" + array[k]);
                } else {
                    System.out.println(array[k]);
                }
            }
        }
    }
}