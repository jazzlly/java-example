package com.leetcode.search.binary;

public class L33SearchRotatedArray {

    public int search(int[] nums, int target) {
        // todo:
        return -1;
    }

    /**
     *
     * @param nums
     * @param left 数组左边界， inclusive
     * @param right 数组有边界，inclusive
     * @param target
     * @return
     */
    public int binarySearch(int[] nums, int left, int right, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (left < 0) {
            left = 0;
        }
        if (right > nums.length -1) {
            right = nums.length -1;
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    /**
     * 找到旋转数组的下标
     * @param rotatedArray
     * @return
     */
    int findPivot(int[] rotatedArray) {
        if (rotatedArray == null || rotatedArray.length == 0) {
            return 0;
        }

        int left = 0;
        int right = rotatedArray.length -1;

        // 没有rotate 或是rotate了0
        if (rotatedArray[left] < rotatedArray[right]) {
            return 0;
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (rotatedArray[mid] > rotatedArray[mid + 1]) {
                return mid + 1;
            }

            if (rotatedArray[mid] > rotatedArray[left]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
