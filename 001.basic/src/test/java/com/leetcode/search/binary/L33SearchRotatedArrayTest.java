package com.leetcode.search.binary;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class L33SearchRotatedArrayTest {

    @Test
    public void binarySearch() {
        L33SearchRotatedArray test = new L33SearchRotatedArray();

        int[] nums = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < nums.length; i++) {
            assertThat(test.binarySearch(nums,
                    0, nums.length - 1, i)).isEqualTo(i);
        }

        assertThat(test.binarySearch(
                null, 0, 0, 1)).isEqualTo(-1);
        assertThat(test.binarySearch(
                new int[] {}, 0, 0, 1)).isEqualTo(-1);

        assertThat(test.binarySearch(
                new int[] {0}, 0, 0, 1)).isEqualTo(-1);

        assertThat(test.binarySearch(
                new int[] {0}, 0, 0, 0)).isEqualTo(0);

        assertThat(test.binarySearch(
                new int[] {0, 1}, 0, 0, 0)).isEqualTo(0);
        assertThat(test.binarySearch(
                new int[] {0, 1}, 0, 0, 1)).isEqualTo(-1);

        assertThat(test.binarySearch(
                new int[] {0, 1}, 0, 1, 1)).isEqualTo(1);

    }
}