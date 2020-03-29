package com.leetcode.recursion;


import org.junit.Test;

public class L77CombinationTest {

    @Test
    public void smoke() {
        System.out.println(new L77Combination().combine(3, 1));
        System.out.println(new L77Combination().combine(3, 2));
        System.out.println(new L77Combination().combine(4, 2));
        System.out.println(new L77Combination().combine(3, 3));
        System.out.println(new L77Combination().combine(5, 3));
    }
}