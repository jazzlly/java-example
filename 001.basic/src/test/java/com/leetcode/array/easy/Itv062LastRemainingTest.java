package com.leetcode.array.easy;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Itv062LastRemainingTest {
    /*
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，
     * 则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     *
     * 示例 1：
     * 输入: n = 5, m = 3
     * 输出: 3
     * 示例 2：
     *
     * 输入: n = 10, m = 17
     * 输出: 2
     *
     * 限制：
     * 1 <= n <= 10^5
     * 1 <= m <= 10^6
     */
    @Test
    public void smoke() {
        assertThat(new Itv062LastRemaining().lastRemaining(5, 3)).isEqualTo(3);
    }
}