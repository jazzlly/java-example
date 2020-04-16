package com.leetcode.dp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L64MinPathSumTest {

    L64MinPathSum test = new L64MinPathSum();

    /*
     * 输入:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * 输出: 7
     * 解释: 因为路径 1→3→1→1→1 的总和最小。
     */
    @Test
    public void smoke() {
        int[][] grid = new int[][] {
                {1,3,1},
                {1,5,1},
                {4,2,1}};
        assertThat(test.minPathSum(grid)).isEqualTo(7);
    }

    @Test
    public void boundary() {
        int[][] grid = new int[][] {
                {1,3,1}};
        assertThat(test.minPathSum(grid)).isEqualTo(5);

        grid = new int[][] {
                {1},
                {1},
                {4}};
        assertThat(test.minPathSum(grid)).isEqualTo(6);
    }
}