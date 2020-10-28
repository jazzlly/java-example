package com.leetcode.dp;

public class L53MaxSubArray {

    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(dp + nums[i], nums[i]);
            max = Math.max(max, dp);
        }

        return max;
    }
}
