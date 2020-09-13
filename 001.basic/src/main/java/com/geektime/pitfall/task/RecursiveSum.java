package com.geektime.pitfall.task;

import java.math.BigDecimal;
import java.util.concurrent.RecursiveTask;

public class RecursiveSum extends RecursiveTask<BigDecimal> {
    int begin;  // inclusive
    int end;    // exclusive
    long[] nums;

    public RecursiveSum(int begin, int end, long[] nums) {
        this.begin = begin;
        this.end = end;
        this.nums = nums;
    }

    @Override
    protected BigDecimal compute() {
        if (end - begin <= 10) {
            BigDecimal sum = new BigDecimal(0);
            for (int i = begin; i < end; i++) {
                sum = sum.add(new BigDecimal(nums[i]));
            }
            return sum;
        }

        int mid = begin + (end - begin)/2;

        RecursiveSum sum1 = new RecursiveSum(begin, mid, nums);
        RecursiveSum sum2 = new RecursiveSum(mid, end, nums);
        invokeAll(sum1, sum2);

        return sum1.join().add(sum2.join());
    }
}
