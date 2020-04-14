package com.leetcode.dp;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L62UniquePathsTest {
    L62UniquePaths test = new L62UniquePaths();

    @Test
    public void smoke() {
        assertThat(test.uniquePaths(3, 2)).isEqualTo(3);
        assertThat(test.uniquePaths(7, 3)).isEqualTo(28);
    }

    @Test
    public void smoke1() {
        assertThat(test.uniquePaths2(3, 2)).isEqualTo(3);
        assertThat(test.uniquePaths2(7, 3)).isEqualTo(28);
    }
}