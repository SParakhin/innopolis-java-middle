package part01.lesson07.task02;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CalculateFactorial {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        calcuateFactorial(100, BigInteger.ONE);
        long end = System.currentTimeMillis();
        long itog = end - start;
        System.out.println("Время подсчета " + itog);
    }

    /**
     * Метод расчета факториала.
     *
     * @param number
     * @param answer
     * @throws InterruptedException
     * @throws ExecutionException
     */
    static void calcuateFactorial(int number, BigInteger answer) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
        List<Future<BigInteger>> totalTaskResults = new ArrayList<>();
        for (int start = 1; start <= number; start++) {
            int end = start + 1;
            Future<BigInteger> taskResult;
            if (end > start) {
                taskResult = executor.submit(new Multiply(start, 1));
                Callable<String> task = () -> Thread.currentThread().getName();
                Future result = executor.submit(task);
                System.out.println(result.get());
            } else {
                taskResult = executor.submit(new Multiply(start, end));
            }
            totalTaskResults.add(taskResult);
        }
        for (Future<BigInteger> future : totalTaskResults) {
            answer = answer.multiply(future.get());
            executor.shutdown();
        }
        System.out.println("Main " + number + "=" + answer);
    }
}