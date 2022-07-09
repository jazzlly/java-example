package com.leetcode.queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class L542UpdateMatrix {

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }

        int[][] ans = new int[matrix.length][matrix[0].length];
        Queue<int[]> queue = new LinkedList<>();

        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    ans[i][j] = 0;
                    queue.add(new int[]{i, j});
                } else if (matrix[i][j] == 1) {
                    ans[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                for (int k = 0; k < dx.length; k++) {
                    int r = pos[0] + dx[k];
                    int c = pos[1] + dy[k];

                    if (r >= 0 && r < matrix.length &&
                        c >= 0 && c < matrix[0].length && 
                        ans[pos[0]][pos[1]] + 1 < ans[r][c]) {

                        ans[r][c] = ans[pos[0]][pos[1]] + 1;
                        queue.add(new int[] { r, c });
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        L542UpdateMatrix test = new L542UpdateMatrix();
        int[][] ans = test.updateMatrix(new int[][] {
            {0,0,0,1},
            {0,1,0,1},
            {1,1,1,1},
            {1,0,1,1},
            {1,1,1,1},
            {1,1,1,1},
        });
        for (int i = 0; i < ans.length; i++) {
            System.out.println(Arrays.toString(ans[i]));
        }
    }
}
