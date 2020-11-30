package com.leetcode.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. 岛屿数量
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，
 * 并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 *
 * 示例 1:
 *
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * 输出: 1
 * 示例 2:
 *
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * 输出: 3
 */
public class L200IslandNumber1 {

    int[] deltaI = new int[] {0, 0, -1, 1};
    int[] deltaJ = new int[] {-1, 1, 0, 0};

    public int numIslandsDfs(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int ans = 0;
        int row = grid.length;
        int column = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] != '1') {
                    continue;
                }

                expandGrid(i, j, grid);
                ans++;
            }
        }

        return ans;
    }

    private void expandGrid(int i, int j, char[][] grid) {
        Queue<int[]> queue = new LinkedList<>();

        grid[i][j] = '0';
        queue.add(new int[] {i, j});

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int k = 0; k < size; k++) {
                int[] point = queue.poll();

                for (int l = 0; l < deltaI.length; l++) {
                    int ni = point[0] + deltaI[l];
                    int nj = point[1] + deltaJ[l];
                    if (ni >= 0 && ni < grid.length &&
                        nj >= 0 && nj < grid[0].length &&
                            grid[ni][nj] == '1') {
                        grid[ni][nj] = '0';
                        queue.add(new int[] {ni, nj});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        L200IslandNumber1 test = new L200IslandNumber1();
        char[][] chars = {{'1','1','1','1','0'},
                          {'1','1','0','1','0'},
                          {'1','1','0','0','0'},{'0','0','0','0','0'}};
        int ans = test.numIslandsDfs(chars);
        System.out.println(ans);

    }
}
