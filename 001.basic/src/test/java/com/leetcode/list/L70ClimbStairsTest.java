package com.leetcode.list;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L70ClimbStairsTest {
    @Test
    public void name() {
        assertThat(L70ClimbStairs.climbStairs(3)).isEqualTo(3);
        assertThat(L70ClimbStairs.climbStairs(4)).isEqualTo(5);
        assertThat(L70ClimbStairs.climbStairs(5)).isEqualTo(8);
    }
}