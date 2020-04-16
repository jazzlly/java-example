package com.leetcode.dp;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L198HouseRobTest {

    /*
     * 输入: [1,2,3,1]
     * 输出: 4
     * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * 示例 2:
     * 输入: [2,7,9,3,1]
     * 输出: 12
     * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     */
    L198HouseRob test = new L198HouseRob();

    @Test
    public void smoke() {
        assertThat(test.rob(new int[]{1, 2, 3, 1})).isEqualTo(4);
        assertThat(test.rob(new int[]{2, 7, 9, 3, 1})).isEqualTo(12);
    }

    @Test
    public void boundary() {
        assertThat(test.rob(new int[]{})).isEqualTo(0);
        assertThat(test.rob(new int[]{1})).isEqualTo(1);
        assertThat(test.rob(new int[]{1,2})).isEqualTo(2);
    }
}