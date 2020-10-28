package com.leetcode.dp;

public class L152MaxProduct {

    public int maxProduct(int[] nums) {
        int imax = Integer.MIN_VALUE;
        int imin = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length ; i++) {
            imax = Math.max(nums[i], Math.max(imax * nums[i], imin * nums[i]));
            imin = Math.min(nums[i], Math.min(imax * nums[i], imin * nums[i]));
        }

        return imax;
    }
}
