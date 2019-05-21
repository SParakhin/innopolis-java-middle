/**ДЗ_10
 Задание: Перевести одну из предыдущих работ на использование стримов и лямбда-выражений там,
 где это уместно (возможно, жертвуя производительностью)
 Вычисление факториала числа с использованием стримов
 */

package part01.lesson11.task01;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        System.out.println(getFactorial(15000));
    }

    public static BigInteger getFactorial(int n) {
        if(n < 2) return BigInteger.valueOf(1);
        return IntStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger::multiply).get();
    }
}