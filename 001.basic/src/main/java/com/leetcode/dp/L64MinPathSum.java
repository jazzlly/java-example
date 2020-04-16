package com.leetcode.dp;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，
 * 使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class L64MinPathSum {
    /**
     * 定义状态变量sum[i][j]为格子i, j到右下角的最短路径
     * 状态转移方程：sum[i][j] = grid[i][j] + min(sum[i+1][j], sum[i][j+1])
     */
    public int minPathSum2(int[][] grid) {
        assert grid != null;
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[0].length - 1; j >= 0; j--) {
                if (i == dp.length - 1 && j == dp[0].length -1 ) {
                    dp[i][j] = grid[i][j];
                    continue;
                }

                dp[i][j] = grid[i][j] + Math.min(
                        getSum(dp, i + 1, j), getSum(dp, i, j + 1));
            }
        }

        return dp[0][0];
    }

    int getSum(int[][] sum, int row, int col) {
        return (row < sum.length && col < sum[0].length) ?
                sum[row][col] : Integer.MAX_VALUE;
    }

    /**
     * 不分配额外空间
     * @param dp
     * @return
     */
    public int minPathSum(int[][] dp) {
        assert dp != null;
        if (dp.length == 0 || dp[0].length == 0) {
            return 0;
        }

        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[0].length - 1; j >= 0; j--) {
                if (i == dp.length - 1 && j == dp[0].length -1 ) {
                    continue;
                }

                dp[i][j] += Math.min(
                        getSum(dp, i + 1, j), getSum(dp, i, j + 1));
            }
        }
        return dp[0][0];
    }

}
