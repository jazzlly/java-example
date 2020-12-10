package com.leetcode.queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class L733FloodGrid {
    /**
     * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。

给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。

为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，
接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，
重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。

最后返回经过上色渲染后的图像。
     */

    int[] dx = new int[] {-1, 1, 0, 0};
    int[] dy = new int[] {0, 0, -1, 1};

    public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }

        int oldColor = image[sr][sc];
        image[sr][sc] = newColor;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {sr, sc});

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] pos = queue.poll();

                for (int j = 0; j < dx.length; j++) {
                    int row = pos[0] + dx[j];
                    int col = pos[1] + dy[j];

                    if (row >= 0 && row < image.length && 
                        col >= 0 && col < image[0].length && 
                        image[row][col] == oldColor) {
                        image[row][col] = newColor;
                        queue.add(new int[]{row, col}) ;
                    }
                }
            }
        }
        return image;
    }
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }

        int oldColor = image[sr][sc];
        dfs(image, sr, sc, oldColor, newColor);

        return image;
    }

    void dfs(int[][] image, int row, int col, int oldColor, int newColor) {
        image[row][col] = newColor;
        for (int i = 0; i < dx.length; i++) {
            int r = row + dx[i];
            int c = col + dy[i];
            if (r >= 0 && r < image.length && c >= 0 && c < image[0].length && image[r][c] == oldColor) {
                    dfs(image, r, c, oldColor, newColor);
            }   
        }
    }
    
    /*
    image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
输出: [[2,2,2],[2,2,0],[2,0,1]]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/flood-fill
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
[[0,0,0],[0,1,0]]
1
1
2
输出：
*/
    public static void main(String[] args) {
        int[][] image = new int[][] {
            {0,0,0},
            {0,1,0}};

        L733FloodGrid test = new L733FloodGrid();
        int[][] ans = test.floodFill(image, 1, 1, 2);
        System.out.println(Arrays.toString(ans));
        for (int i = 0; i < ans.length; i++) {
            System.out.println(Arrays.toString(ans[i]));
        }
    }
}
