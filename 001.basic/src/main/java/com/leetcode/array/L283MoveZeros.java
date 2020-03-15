package com.leetcode.array;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class L283MoveZeros {
    /**
     * 快慢指针方法
     */
    public static void moveZeroes(int[] nums) {
        assert nums != null;

        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }

            if (i != slow) {
                nums[slow] = nums[i];
                nums[i] = 0;
            }
            slow++;
        }
    }

    public static void moveZeroes2(int[] nums) {
        assert nums != null;

        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else {
                if (zeroCount == 0) {
                    continue;
                } else {
                    nums[i - zeroCount] = nums[i];
                }
            }
        }
        for (int i = 1; i <= zeroCount; i++) {
            nums[nums.length - i] = 0;
        }
    }

    public static void moveZeroes1(int[] nums) {
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                continue;
            }

            if (zeroCount > 0) {
                int tmp = nums[i - zeroCount];
                nums[i - zeroCount] = nums[i];
                nums[i] = tmp;
            }
        }
    }
}
