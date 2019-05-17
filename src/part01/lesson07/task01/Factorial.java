package part01.lesson07.task01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Класс для вычисления факториала
 */
public class Factorial {

    /**
     * Метод для вычисления факториала числа
     * @param input
     * @return
     */
    public static BigInteger calculateFactorial(BigInteger input) {
        BigInteger factorial = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE;
             i.compareTo(input) <= 0;
             i = i.add(BigInteger.ONE)) {
            factorial = factorial.multiply(i);
        }
        return factorial;
    }

    /**
     * Метод для формирования списка чисел для вычисления
     *
     * @param count Количество элементов списка
     * @return Список чисел
     */
    public static List<BigInteger> getBigIntegersList(int count) {
        List<BigInteger> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            list.add(new BigInteger(Integer.toString(i)));
        }
        return list;
    }

    /**
     * Класс для получения результатов вычислений
     */
    static class FactorialTask extends RecursiveTask<BigInteger> {

        private static int THREAD_POOL = Runtime.getRuntime().availableProcessors() - 1;
        private List<BigInteger> integerList;

        FactorialTask(List<BigInteger> integerList) {
            this.integerList = integerList;
        }

        /**
         * Основной метод для параллельного вычисления
         * @return
         */
        @Override
        protected BigInteger compute() {
            if (integerList.size() <= THREAD_POOL) {
                return sumFactorials();
            } else {
                int middle = integerList.size() / 2;
                List<BigInteger> newList = integerList.subList(middle, integerList.size());
                integerList = integerList.subList(0, middle);
                FactorialTask task = new FactorialTask(newList);
                task.fork();
                BigInteger thisSum = this.compute();
                BigInteger thatSum = task.join();
                return thisSum.add(thatSum);
            }
        }

        /**
         * Метод для нахождения суммы факториалов чисел из массива
         *
         * @return
         */
        private BigInteger sumFactorials() {
            BigInteger sum = BigInteger.ZERO;
            Factorial factorial = new Factorial();
            for (BigInteger i : integerList) {
                sum = sum.add(factorial.calculateFactorial(i));
            }
            return sum;
        }
    }
}
