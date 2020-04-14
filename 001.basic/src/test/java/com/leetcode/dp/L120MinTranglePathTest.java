package com.leetcode.dp;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class L120MinTranglePathTest {
    L120MinTranglePath test = new L120MinTranglePath();

    /**
     * * 例如，给定三角形：
     *  *
     *  * [
     *  *      [2],
     *  *     [3,4],
     *  *    [6,5,7],
     *  *   [4,1,8,3]
     *  * ]
     *  * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     */
    @Test
    public void smoke() {
        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3));
        assertThat(test.minimumTotal(lists)).isEqualTo(11);

    }
}