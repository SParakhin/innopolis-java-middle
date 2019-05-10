package part01.lesson05.task03.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Утилитный класс для автогенерации ID при создании объекта Animal
 */
public class Sequence {

    private final AtomicInteger counter = new AtomicInteger();

    public int nextValue() {
        return counter.getAndIncrement();
    }
}