package com.geektime.pitfall;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
public class P001ForkJoinPool {
        
    private static final ThreadLocal<String> currentUser =
        ThreadLocal.withInitial(() -> null);

    public static void main(String[] args) throws InterruptedException {
        P001ForkJoinPool test = new P001ForkJoinPool();
        test.test();
    }

    public void test() throws InterruptedException {
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

}
