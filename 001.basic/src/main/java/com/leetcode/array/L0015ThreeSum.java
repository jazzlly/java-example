package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L0015ThreeSum {

    /**
     * 排序+双指针靠近
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            // 头指针处值大于0， 则其后所有指针都大于0
            if (nums[i] > 0) {
                break;
            }

            // 忽略重复的头指针值
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int low = i + 1;
            int high = nums.length - 1;

            while (low < high) {
                boolean moveLow = false;
                boolean moveHigh = false;

                int sum = nums[i] + nums[low] + nums[high];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    moveLow = moveHigh = true;
                } else {
                    if (sum > 0) {
                        moveHigh = true;
                    } else {
                        moveLow = true;
                    }

                }

                if (moveLow) {
                    while (low < high && nums[low] == nums[++low]) {}
                }
                if (moveHigh) {
                    while (high > low && nums[high] == nums[--high]) {}
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = L0015ThreeSum.threeSum(
                new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(lists);
    }
}
