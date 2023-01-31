package com.leetcode.array;


import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class L26RemoveDupNumFromSortedArrayTest {

    @Test
    public void test() {
        int[] array = new int[]{1,1,2};
        assertThat(L26RemoveDupNumFromSortedArray2.removeDuplicates(array)).isEqualTo(2);
        assertThat(Arrays.copyOfRange(array, 0, 2))
                .containsExactly(1,2);
    }

    @Test
    public void test1() {
        int[] array = new int[]{0,0,1,1,1,2,2,3,3,4};
        assertThat(L26RemoveDupNumFromSortedArray2.removeDuplicates(array)).isEqualTo(5);
        assertThat(Arrays.copyOfRange(array, 0, 5))
                .containsExactly(0,1,2,3,4);
    }

}