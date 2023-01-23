package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L0015ThreeSum2 {

    /**
     * 排序+双指针靠近
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }

            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while (low < high && nums[low] == nums[low + 1]) low++;
                    while (low < high && nums[high] == nums[high - 1]) high--;
                    low++;
                    high--;
                } else if (sum > 0) {
                    high--;
                } else {
                    low++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = L0015ThreeSum2.threeSum(
                new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(lists);
    }
}
