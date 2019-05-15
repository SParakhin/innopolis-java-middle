/**
 * Дан массив случайных чисел. Написать программу для вычисления факториалов всех элементов массива.
 * Использовать пул потоков для решения задачи.
 * По сути, есть несколько способа решения задания:
 *
 * 1) распараллеливать вычисление факториала для одного числа
 *
 * 2) распараллеливать вычисления для разных чисел
 *
 * 3) комбинированный
 *
 * При чем вычислив факториал для одного числа, можно запомнить эти данные и использовать их для вычисления другого,
 * что будет гораздо быстрее
 */

package part01.lesson07.task01;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        System.out.println(getFactorial(200));
    }

    public static BigInteger getFactorial(int n) {
        if(n < 2) return BigInteger.valueOf(1);
        return IntStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger::multiply).get();
    }
}