package com.leetcode.heap;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class L347TopKFreqTest {

    @Test
    public void smoke() {
        L347TopKFreq test = new L347TopKFreq();
        int[] ret = test.topKFrequent(
                new int[] {3,0,1,0}, 1);
        System.out.println(Arrays.toString(ret));
    }
}