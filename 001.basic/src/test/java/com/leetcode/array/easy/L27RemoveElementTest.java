package com.leetcode.array.easy;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L27RemoveElementTest {

    @Test
    public void smoke() {
        L27RemoveElement test = new L27RemoveElement();

        //给定 nums = [3,2,2,3], val = 3,
        int[] nums = new int[]{3, 2, 2, 3};
        assertThat(test.removeElement(nums, 3)).isEqualTo(2);

        // nums = [0,1,2,2,3,0,4,2], val = 2,
        nums = new int[] {0,1,2,2,3,0,4,2};
        assertThat(test.removeElement(nums, 2)).isEqualTo(5);

    }



}