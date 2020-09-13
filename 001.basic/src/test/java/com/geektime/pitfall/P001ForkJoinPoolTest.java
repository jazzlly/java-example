package com.geektime.pitfall;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class P001ForkJoinPoolTest {

    private static final ThreadLocal<String> currentUser =
            ThreadLocal.withInitial(() -> null);

    @Test
    public void smoke() throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);

        forkJoinPool.execute(() ->
                IntStream.rangeClosed(1, 10).parallel().forEach(value -> {
                    System.out.println("begin: -------------------");
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("initial value: " + currentUser.get());
                    currentUser.set(Thread.currentThread().getName() + ":" + value);
                    System.out.println(currentUser.get());
                    System.out.println("end: -----------------------");;
                }));

        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
    }

    @Test
    public void smoke2() {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

    }
}