package com.geektime.pitfall.task;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.assertThat;

public class RecursiveSumTest {

    public static final int ELEMENT_COUNT = 100000000;
    Random random = new Random();

    @Test
    public void smokeLessThan10() {

        long[] nums = new long[10];
        int sumRaw = 0;
        for (int i = 0; i < 10; i++) {
            nums[i] = random.nextInt(100);
            sumRaw += nums[i];
        }

        ForkJoinPool pool = new ForkJoinPool();
        BigDecimal sum = pool.invoke(new RecursiveSum(0, nums.length, nums));
        System.out.println(sum);
        System.out.println(sumRaw);
        assertThat(sum).isEqualTo(new BigDecimal(sumRaw));
    }

    @Test
    public void smoke1W() {
        long[] nums = new long[ELEMENT_COUNT];
        BigDecimal sumRaw = new BigDecimal(0);
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            nums[i] = random.nextInt(20);
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
           sumRaw = sumRaw.add(new BigDecimal(nums[i]));
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTime());

        stopWatch = new StopWatch();
        stopWatch.start();
        ForkJoinPool pool = new ForkJoinPool();
        BigDecimal sum = pool.invoke(new RecursiveSum(0, nums.length, nums));
        assertThat(sum).isEqualTo(sumRaw);
        stopWatch.stop();
        System.out.println(stopWatch.getTime());

    }
}