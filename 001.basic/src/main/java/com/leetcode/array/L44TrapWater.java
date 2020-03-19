package com.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class L44TrapWater {
    /**
     * 按行计算
     * 时间复杂度 O(n * m), m为数组中的最大值
     * 空间复杂度 O(1)
     *
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        assert height != null;
        if (height.length <= 2) {
            return 0;
        }

        int max = 0;
        for (int i : height) {
            if (max < i) {
                max = i;
            }
        }

        int h = 0;
        int ans = 0;
        while (h < max) {
            int tmp = 0;
            boolean addWater = false;
            for (int i = 1; i < height.length; i++) {
                if (height[i] <= h) {
                    if (height[i - 1] > h) {
                        addWater = true;
                    }
                    if (addWater) {
                        tmp++;
                    }
                }

                if (addWater && height[i] > h) {
                    addWater = false;
                    ans += tmp;
                    tmp = 0;
                }
            }
            h++;
        }
        return ans;
    }

    public int trap2(int[] height) {
        int water = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = 0;
            int rightMax = 0;
            for (int j = 0; j < i; j++) {
                if (height[j] > leftMax) {
                    leftMax = height[j];
                }
            }

            if (height[i] >= leftMax) {
                continue;
            }

            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > rightMax) {
                    rightMax = height[j];
                }
            }

            if (height[i] >= rightMax) {
                continue;
            }

            water += Math.min(leftMax, rightMax) - height[i];
        }

        return water;
    }

    Map<Integer, Integer> leftMaxMap = new HashMap<>();
    Map<Integer, Integer> rightMaxMap = new HashMap<>();

    void genLeftMaxMap(int[] heigth) {

    }
    public int trap(int[] height) {
        int water = 0;
        genMaxMap(height, )


        for (int i = 0; i < height.length; i++) {
            int leftMax = 0;
            int rightMax = 0;


            water += Math.min(leftMax, rightMax) - height[i];
        }

        return water;
    }

}
