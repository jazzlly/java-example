package com.leetcode.stack;

public class L84MaxRectangleArea {

    /**
     * 暴力解法
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        int max = 0;

        for (int i = 0; i < heights.length; i++) {
            int left = i;
            int right = i;

            // 向左边扩散
            for (int j = i - 1; j >= 0; j--) {
                if (heights[j] < heights[i]) {
                    break;
                }

                left = j;
            }
            // 向右边扩散
            for (int j = i + 1; j < heights.length; j++) {
                if (heights[j] < heights[i]) {
                    break;
                }
                right = j;
            }
            max = Math.max(max, heights[i] * (right - left + 1));
        }

        return max;
    }
}
