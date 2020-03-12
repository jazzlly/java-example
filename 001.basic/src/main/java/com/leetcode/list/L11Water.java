package com.leetcode.list;

public class L11Water {

    public static int maxArea(int[] height) {
        int head = 0;
        int tail = height.length - 1;

        int max = 0;
        while (head < tail) {
            int minHeight = Math.min(height[head], height[tail]);
            int area = (tail - head) * minHeight;
            max = Math.max(max, area);
            if (height[head] < height[tail]) {
                head++;
            } else {
                tail--;
            }
        }

        return max;
    }
}
