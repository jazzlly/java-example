package com.leetcode.dp;

public class L62UniquePaths {

    /**
     * 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     *
     * 问总共有多少条不同的路径？
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }

        int[][] grids = new int[m][n];
        for (int i = 0; i < n; i++) {
            grids[m - 1][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            grids[i][n - 1] = 1;
        }
        for (int i = m - 2; i >=0 ; i--) {
            for (int j = n - 2; j >= 0 ; j--) {
                grids[i][j] = grids[i+1][j] + grids[i][j+1];
            }
        }
        /*
        for (int i = 0; i < grids.length; i++) {
            System.out.println(Arrays.toString(grids[i]));
        }
         */

        return grids[0][0];
    }

    /**
     * 节省空间做法
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }

        int[] up = new int[n];
        int[] down = new int[n];
        for (int i = 0; i < n; i++) {
            down[i] = 1;
        }
        up[n - 1] = 1;

        for (int i = m - 2; i >=0 ; i--) {
            for (int j = n - 2; j >= 0 ; j--) {
                up[j] = down[j] + up[j+1];
            }
            int[] tmp = up;
            up = down;
            down = tmp;
        }
        return down[0];
    }
}
