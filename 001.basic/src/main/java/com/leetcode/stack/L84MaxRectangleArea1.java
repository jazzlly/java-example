package com.leetcode.stack;

import java.util.Stack;

public class L84MaxRectangleArea1 {

    /**
     * 暴力解法
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        /*
        遍历每一个柱子
            如果左边.l柱子大于等于高度，向左搜索
            如果右边柱子大于等于高度，向右搜索
            两边搜索完成后，算出max
         */
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int left = i;
            int right = i;
            while (left - 1 >= 0 && heights[left - 1] >= heights[i]) {
                left--;
            }
            while (right + 1 < heights.length && heights[right + 1] >= heights[i]) {
                right++;
            }
            maxArea = Math.max(maxArea, heights[i] * (right - left + 1));
        }

        return maxArea;
    }


    public static int largestRectangleArea1(int[] heights) {

        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int minHigh = heights[i];

            for (int j = i; j < heights.length ; j++) {
                minHigh = Math.min(minHigh, heights[j]);
                maxArea = Math.max(maxArea, minHigh * (j - i + 1));
            }
        }

        return maxArea;
    }


    public static int largestRectangleArea2(int[] heights) {
        int maxArea = 0;

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{-1, 0});

        int i = 0;
        while (!stack.empty()) {
            int height = i < heights.length ? heights[i] : -1;

            if (height >= stack.peek()[1]) {
                stack.push(new int[]{i, height});
                i++;
                continue;
            }

            while (height < stack.peek()[1]) {
                int[] pop = stack.pop();
                if (pop[0] == -1) {
                    break;
                }
                maxArea = Math.max(maxArea, pop[1] * (i - stack.peek()[0] - 1));
            }
        }

        return maxArea;
    }
}
