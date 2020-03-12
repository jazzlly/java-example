package com.leetcode.array;

public class L189ArrayRotate {
    public static void rotate1(int[] nums, int k) {
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

    // 三次翻转法
    public void rotate(int[] nums, int k) {
        if (nums.length <= 1) {
            return;
        }
        if (k >= nums.length) {
            k = k % nums.length;
        }
        if (k == 0) {
            return;
        }

        int[] heads = new int[] {0, 0, k};
        int[] tails = new int[]{nums.length - 1, k - 1, nums.length - 1};

        for (int i = 0; i < heads.length; i++) {
            int head = heads[i];
            int tail = tails[i];

            while (head < tail) {
                swap(nums, head, tail);
                head++;
                tail--;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


}
