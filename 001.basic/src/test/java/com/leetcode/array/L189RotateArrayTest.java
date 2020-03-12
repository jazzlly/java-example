package com.leetcode.array;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L189RotateArrayTest {
    @Test
    public void test() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        new L189RotateArray().rotate(array, 3);
        assertThat(array).containsExactly(5,6,7,1,2,3,4);
    }

    @Test
    public void test1() {
        int[] array = new int[]{-1, -100, 3, 99};
        new L189RotateArray().rotate(array, 2);
        assertThat(array).containsExactly(3, 99, -1, -100);
    }
}