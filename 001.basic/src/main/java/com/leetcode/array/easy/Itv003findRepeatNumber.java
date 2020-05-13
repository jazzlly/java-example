package com.leetcode.array.easy;

import java.util.*;

public class Itv003findRepeatNumber {

    /**
     * 面试题03. 数组中重复的数字
     * 找出数组中重复的数字。
     *
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
     * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * 示例 1：
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     *  0  1  2  3  4  5  6
     *  1  0  2  3
     * 输出：2 或 3
     *
     * 限制：
     *
     * 2 <= n <= 100000
     *
     * 执行用时g:2ms,在所有Java提交中击败了70.99%的用户
     * 内存消耗g:47.5MB,在所有Java提交中击败了100.00%的用户
     */
    public int findRepeatNumber(int[] nums) {
        assert nums != null && nums.length >= 2;
        int[] bits = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (bits[num] == 1) {
                return num;
            }
            bits[num]++;
        }

        throw new IllegalArgumentException("no answer!");
    }

    /**
     * slow:
     * 执行用时 : 13 ms 在所有 Java 提交中击败了13.30%的用户
     * 内存消耗 :48.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int findRepeatNumberHashMap(int[] nums) {
        assert nums != null && nums.length >= 2;
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int cnt = indexMap.getOrDefault(num, 0);
            if (cnt == 1) {
                return num;
            }

            indexMap.put(num,  cnt + 1);
        }

        throw new IllegalArgumentException("no answer!");
    }


    /*
        执行用时:4ms,在所有Java提交中击败了53.97%的用户
        内存消耗:47.7MB,在所有Java提交中击败了100.00%的用户
     */
    public int findRepeatNumberBitSet(int[] nums) {
        assert nums != null && nums.length >= 2;
        BitSet bits = new BitSet(nums.length);

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (bits.get(num)) {
                return num;
            }
            bits.set(num);
        }

        throw new IllegalArgumentException("no answer!");
    }

    public int findRepeatNumberSet(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int repeat = -1;
        for (int num : nums) {
            if (!set.add(num)) {
                repeat = num;
                break;
            }
        }
        return repeat;
    }
}
