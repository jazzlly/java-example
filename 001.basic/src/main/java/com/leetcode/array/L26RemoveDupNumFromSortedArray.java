package com.leetcode.array;

/**
 * 26. 删除排序数组中的重复项
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class L26RemoveDupNumFromSortedArray {

    /**
     * 快慢指针法
     * 时间复杂度O(n), 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates1(int[] nums) {
        assert nums != null;
        if (nums.length == 0) {
            return 0;
        }

        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            // 遇到重复数值时，快指针前进
            if (nums[slow] == nums[fast]) {
                continue;
            }
            // 数值不同时，慢指针前进，并将快指针指向数值复制到慢指针位置
            nums[++slow] = nums[fast];
        }
        return slow + 1;
    }


    /**
     * [0,0,1,1,1,2,2,3,3,4],
     * [0,1,2,3,4]
     * 0 1 2 3 0 0 1 1 2 2 2 3
     * 1 1
     * @param nums
     * @return
     */

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int slow = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[slow] == nums[i]) {
                continue;
            } else {
                nums[++slow] = nums[i];
            }
        }
        return slow + 1;
    }







}
