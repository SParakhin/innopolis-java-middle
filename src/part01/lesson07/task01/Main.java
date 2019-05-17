package part01.lesson07.task01;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.*;

import static part01.lesson07.task01.Factorial.getBigIntegersList;

public class Main {

    public static void main(String[] args) {

        List<BigInteger> list = getBigIntegersList(4);
        BigInteger sum = ForkJoinPool.commonPool().
                invoke(new Factorial.FactorialTask(list));
        System.out.println("Сумма факториалов массива = " + sum);
    }

}