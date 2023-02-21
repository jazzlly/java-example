package com.leetcode.stack;

import org.junit.Test;

import static org.junit.Assert.*;

public class L84MaxRectangleAreaTest {

    @Test
    public void smoke() {
        System.out.println(L84MaxRectangleArea1.largestRectangleArea2(
                new int[] {2,1,5,6,2,3}));
    }

    @Test
    public void smoke2() {
        System.out.println(L84MaxRectangleArea1.largestRectangleArea2(
                new int[] {2,1,2}));
    }
}