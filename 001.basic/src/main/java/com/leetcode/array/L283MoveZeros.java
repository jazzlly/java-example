package com.leetcode.array;

public class L283MoveZeros {

    public static void moveZeroes1(int[] nums) {
        if (nums.length <= 1) {
            return;
        }

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

    public static void moveZeroes(int[] nums) {
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

    public static void moveZeroes2(int[] nums) {
        int slowPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != slowPos) {
                    nums[slowPos] = nums[i];
                    nums[i] = 0;
                }
                slowPos++;
            }
        }
    }
}
