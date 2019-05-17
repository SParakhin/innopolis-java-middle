package part01.lesson07.task02;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public class Multiply implements Callable<BigInteger> {
    private int start;
    private int end;


    public Multiply(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public BigInteger call() {
        BigInteger a = BigInteger.valueOf(start);
        BigInteger b = BigInteger.valueOf(end);
        return a.multiply(b);
    }
}
