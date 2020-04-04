package com.leetcode.search.binary;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L33SearchRotatedArrayTest {

    @Test
    public void smoke() {
        L33SearchRotatedArray test = new L33SearchRotatedArray();

        // 数组没有rotate场景
        assertThat(test.search(
                new int[] {0, 1, 2, 3, 4, 5, 6, 7}, 0))
                .isEqualTo(0);
        assertThat(test.search(
                new int[] {0, 1, 2, 3, 4, 5, 6, 7}, 7))
                .isEqualTo(7);
        assertThat(test.search(
                new int[] {0, 1, 2, 3, 4, 5, 6, 7}, 4))
                .isEqualTo(4);
    }

    @Test
    public void searchRotated() {
        L33SearchRotatedArray test = new L33SearchRotatedArray();
        int[] nums =  new int[] {7, 8, 9, 10, 11, 12, 0, 1, 2, 3, 4, 5};

        assertThat(test.search(nums, 7)).isEqualTo(0);
        assertThat(test.search(nums, 5)).isEqualTo(nums.length - 1);
        assertThat(test.search(nums, 0)).isEqualTo(6);
        assertThat(test.search(nums, 9)).isEqualTo(2);
        assertThat(test.search(nums, 3)).isEqualTo(nums.length - 3);
    }
}