package com.geektime.pitfall.task;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class RecursiveDivideActionTest {

    @Test
    public void smoke() throws InterruptedException {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new RecursiveDivideAction(4096));

        Thread.sleep(1000);
        System.out.println(pool.getActiveThreadCount());
        System.out.println(pool.getPoolSize());
        System.out.println(pool.getParallelism());
        System.out.println(pool.getQueuedTaskCount());
        System.out.println(pool.getStealCount());
        pool.awaitTermination(1, TimeUnit.HOURS);
    }
}