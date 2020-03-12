package com.leetcode.array;

public class L189ArrayRotate {
    public static void rotate(int[] nums, int k) {

        int tmp[] = new int[nums.length];
        for (int i = 0; i < nums.length -k ; i++) {
            tmp[k+i] = nums[i];
        }
        for (int i = 0; i < k; i++) {
            tmp[i] = nums[nums.length - k + i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = tmp[i];
        }
    }
}
