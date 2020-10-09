package com.leetcode.dp;

public class L300LongAscSequence {

    public int lengthOfLIS1(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }

        int max = 1;
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i ; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }

    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }

        int[] tails = new int[nums.length];
        tails[0] = nums[0];
        int len = 1;

        for (int i = 1; i < nums.length; i++) {
            if (tails[len - 1] < nums[i]) {
                tails[len++] = nums[i];
                continue;
            }
            for (int j = 0; j < len; j++) {
                if (tails[j] >= nums[i] ) {
                    tails[j] = nums[i];
                    break;
                }
            }
        }
        return len;
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }

        int[] tails = new int[nums.length];
        tails[0] = nums[0];
        int len = 1;

        for (int i = 1; i < nums.length; i++) {
            int l = 0;
            int r = len;
            while (l < r) {
                int m = l + (r - l)/2;

                if (tails[m] == nums[i]) {
                    r = m;
                    break;
                }
                if (nums[i] > tails[m]) {
                    l = m + 1;
                    continue;
                }
                if (nums[i] < tails[m]) {
                    r = m ;
                }
            }

            tails[r] = nums[i];
            len = Math.max(len, r+1);
        }
        return len;
    }

    public static void main(String[] args) {
        L300LongAscSequence test = new L300LongAscSequence();
        System.out.println(test.lengthOfLIS(new int[] {10,9,2,5,3,7,101,18}));
        System.out.println(test.lengthOfLIS(new int[] {4,10,4,3,8,9}));
    }
}
