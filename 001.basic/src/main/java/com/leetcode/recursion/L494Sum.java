package com.leetcode.recursion;

import java.util.LinkedList;

public class L494Sum {
    int ans = 0;

    public int findTargetSumWays(int[] nums, int S) {
        recursion(nums, S, 0, 0);
        return ans;
    }

    void recursion(int[] nums, int S, int pos, int current) {
        if (pos == nums.length && current == S) {
            ans++;
            return;
        }

        if (pos >= nums.length) {
            return;
        }
        
        recursion(nums, S, pos + 1, current + nums[pos]);
        recursion(nums, S, pos + 1, current - nums[pos]);
    }

    public static void main(String[] args) {
        L494Sum test = new L494Sum();
        // System.out.println(test.findTargetSumWays(new int[] { 1, 1}, 2));
        System.out.println(test.findTargetSumWays(new int[] { 1, 1, 1, 1, 1}, 3));
    }
}
