package com.leetcode.hash;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L169MajorityNumTest {
    /*
     * 示例 1:
     * 输入: [3,2,3]
     * 输出: 3
     *
     * 示例 2:
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     */
    @Test
    public void smoke() {
        assertThat(new L169MajorityNum().majorityElement(
                new int[] {3,2,3})).isEqualTo(3);

        assertThat(new L169MajorityNum().majorityElement(
                new int[] {2,2,1,1,1,2,2})).isEqualTo(2);
    }

}