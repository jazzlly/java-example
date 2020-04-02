package com.leetcode.search.binary;

public class L33SearchRotatedArray {

    public int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        if (nums[left] < nums[right])
            return 0;

        while (left <= right) {
            int pivot = (left + right) / 2;
            if (nums[pivot] > nums[pivot + 1])
                return pivot + 1;
            else {
                if (nums[pivot] < nums[left])
                    right = pivot - 1;
                else
                    left = pivot + 1;
            }
        }
        return 0;
    }

    public int search(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int pivot = left + (right - left) / 2;
            if (nums[pivot] == target)
                return pivot;
            else {
                if (target < nums[pivot])
                    right = pivot - 1;
                else
                    left = pivot + 1;
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int n = nums.length;

        if (n == 0)
            return -1;
        if (n == 1)
            return nums[0] == target ? 0 : -1;

        int rotate_index = findPivot(nums);

        // if target is the smallest element
        if (nums[rotate_index] == target)
            return rotate_index;
        // if array is not rotated, search in the entire array
        if (rotate_index == 0)
            return search(nums, 0, n - 1, target);

        if (target < nums[0])
            // search in the right side
            return search(nums, rotate_index, n - 1, target);

        // search in the left side
        return search(nums, 0, rotate_index, target);
    }
}

