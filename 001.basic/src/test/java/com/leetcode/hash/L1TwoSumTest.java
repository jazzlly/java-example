package com.leetcode.hash;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class L1TwoSumTest {

    @Test
    public void smoke() {
        assertThat(L1TwoSum.twoSum(new int[]{2, 7, 11, 15}, 9))
                .containsExactlyInAnyOrder(0, 1);

        assertThat(L1TwoSum.twoSum(new int[]{2, 2, 11, 7}, 9))
                .containsExactlyInAnyOrder(1, 3);
    }
}