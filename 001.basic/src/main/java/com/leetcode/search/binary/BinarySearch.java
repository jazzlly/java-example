package com.leetcode.search.binary;

import static org.assertj.core.api.Assertions.assertThat;

public class BinarySearch {

    static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    static int leftBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid -  1;
            }
        }

        if (left >= nums.length || nums[left] != target) {
            return -1; 
        }
        return left;
    }

    static int rightBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid -  1;
            }
        }

        if (right < 0 || nums[right] != target) {
            return -1; 
        }
        return right;
    }

    /**
     * 返回和target最近的位置
     * @param nums
     * @param target
     * @return
     */
    static int nearPostion(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid -  1;
            }
        }

        if (left >= nums.length) {
            return nums.length - 1;
        }
        if (right < 0) {
            return 0;
        }
        return right;
    }

    public static void main(String[] args) {
        // assertThat(binarySearch(new int[] {}, 1)).isEqualTo(-1);
        // assertThat(binarySearch(new int[] {1}, 1)).isEqualTo(0);
        // assertThat(binarySearch(new int[] {1}, 0)).isEqualTo(-1);
        // assertThat(binarySearch(new int[] {1}, 2)).isEqualTo(-1);

        // assertThat(leftBound(new int[] {2,3,5,7}, 1)).isEqualTo(-1);
        // assertThat(leftBound(new int[] {1,2,2,2,2,2,2,3,5,7}, 2)).isEqualTo(1);
        // assertThat(rightBound(new int[] {1,2,2,2,2,2,2,3,5,7}, 2)).isEqualTo(6);

        assertThat(nearPostion(new int[] {0}, -1)).isEqualTo(0);
        
        assertThat(nearPostion(new int[] {}, 2)).isEqualTo(-1);
        assertThat(nearPostion(new int[] {0}, 0)).isEqualTo(0);
        assertThat(nearPostion(new int[] {0}, 5)).isEqualTo(0);

        assertThat(nearPostion(new int[] {2,4,4,4,4,6,8,10}, 5)).isEqualTo(4);
        // assertThat(nearPostion(new int[] {0}, 5)).isEqualTo(0);
    }
    
}
