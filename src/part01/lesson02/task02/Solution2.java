/**
  Задание 2.
  Составить программу, генерирующую N случайных чисел.
  Для каждого числа k вычислить квадратный корень q.
  Если квадрат целой части q числа равен k, то вывести это число на экран.
  Предусмотреть что первоначальные числа могут быть отрицательные, в этом случае генерировать исключение.
 */
package part01.lesson02.task02;

import java.util.Random;

import static java.lang.Math.*;

public class Solution2 {
    public static void main(String[] args) {

        Integer[] array = new Integer[1000];
        Random random = new Random();
        if (random.nextInt() < 0) {
            throw new IllegalArgumentException();
        }

        for (int k = 0; k < 1000; k++) {
            array[k] = random.nextInt(10000);
            int q = (int) sqrt(array[k]);
            if (array[k] == q * q) {
                System.out.println(q + "*" + q + "=" + array[k]);
            }
        }
    }
}
