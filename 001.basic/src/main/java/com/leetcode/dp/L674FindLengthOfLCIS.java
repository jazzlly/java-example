package com.leetcode.dp;

import static org.assertj.core.api.Assertions.assertThat;

public class L674FindLengthOfLCIS {

    public int findLengthOfLCIS1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = 1;

        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int dp0 = 1;
        int dp1 = 1;
        int max = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                dp1 = dp0 + 1; 
            } else {
                dp1 = 1;
            }
            max = Math.max(max, dp1);
            dp0 = dp1;
        }

        return max;
    }

    public static void main(String[] args) {
        L674FindLengthOfLCIS test = new L674FindLengthOfLCIS();
        assertThat(test.findLengthOfLCIS(new int[]{})).isEqualTo(0);
        assertThat(test.findLengthOfLCIS(new int[]{1})).isEqualTo(1);
        assertThat(test.findLengthOfLCIS(new int[]{1,1})).isEqualTo(1);
        assertThat(test.findLengthOfLCIS(new int[]{1,3,5,4,7})).isEqualTo(3);
    }
    
}
