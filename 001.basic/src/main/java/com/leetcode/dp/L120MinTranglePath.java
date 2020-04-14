package com.leetcode.dp;

import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，
 * 那么你的算法会很加分。
 *
 * // todo: 优化一下时间
 * 执行结果：
 * 通过
 * 显示详情
 * 执行用时 :
 * 11 ms
 * , 在所有 Java 提交中击败了
 * 6.25%
 * 的用户
 * 内存消耗 :
 * 39.9 MB
 * , 在所有 Java 提交中击败了
 * 8.70%
 * 的用户
 */
public class L120MinTranglePath {
    public int minimumTotal(List<List<Integer>> triangle) {
        assert triangle != null && triangle.size() >= 1;

        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }

        // 初始化grid
        int[][] grid = new int[triangle.size()][triangle.size()];
        for (int row = 0; row < triangle.size(); row++) {
            List<Integer> list = triangle.get(row);
            for (int col = 0; col < list.size(); col++) {
                grid[row][col] = list.get(col);
            }
        }

        for (int row = triangle.size() - 2; row >= 0; row--) {
            List<Integer> list = triangle.get(row);
            for (int col = 0; col < list.size(); col++) {
                grid[row][col] += Math.min(grid[row + 1][col], grid[row + 1][col + 1]);
            }
        }

        for (int i = 0; i < grid.length; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
        return grid[0][0];
    }
}
