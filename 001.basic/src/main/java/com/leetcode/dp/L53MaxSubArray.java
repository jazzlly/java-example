package com.leetcode.dp;

import static org.assertj.core.api.Assertions.assertThat;

public class L53MaxSubArray {

    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length + 1];
        dp[0] = 0;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            dp[i + 1] = Math.max(dp[i] + nums[i], nums[i]);
            max = Math.max(max, dp[i + 1]);
        }

        return max;
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int dp0 = 0;
        int dp1 = 0;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            dp1 = Math.max(dp0 + nums[i], nums[i]);
            max = Math.max(max, dp1);
            dp0 = dp1;
        }

        return max;
    }

    public static void main(String[] args) {
        L53MaxSubArray test = new L53MaxSubArray();
        assertThat(test.maxSubArray(null)).isEqualTo(0);
        assertThat(test.maxSubArray(new int[] {})).isEqualTo(0);
        assertThat(test.maxSubArray(new int[] {0})).isEqualTo(0);
        assertThat(test.maxSubArray(new int[] {1})).isEqualTo(1);
        assertThat(test.maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5,4}))
            .isEqualTo(6);
    }
}
