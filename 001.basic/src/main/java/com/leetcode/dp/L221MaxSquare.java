package com.leetcode.dp;

public class L221MaxSquare {

    /**
     * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
     *
     * 输入:
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     * 输出: 4
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        assert matrix != null && matrix[0] != null;
        
        int[][] dp = new int[matrix.length][matrix[0].length];

        return -1;
    }
}
