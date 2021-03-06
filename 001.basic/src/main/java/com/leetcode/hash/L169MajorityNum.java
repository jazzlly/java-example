package com.leetcode.hash;

import java.util.HashMap;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class L169MajorityNum {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int majority = 0;
        int maxCount = 0;

        for (int i = 0; i < nums.length; i++) {
            int count = 1;
            if (map.containsKey(nums[i])) {
                count = map.get(nums[i]) + 1;
            }

            map.put(nums[i], count);
            if (count > maxCount) {
                maxCount = count;
                majority = nums[i];
            }

            if (maxCount >= nums.length/2 + 1) {
                return majority;
            }
        }

        return majority;
    }
}
