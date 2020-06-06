package com.leetcode.array;

/**
 * 66. 加一
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 */
public class L66PlusOne {

    /**
     * 时间复杂度 O(n), 空间复杂度: 一般情况O(1), 特殊情况O(n)
     */
    public int[] plusOne1(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;

            // 发生进位
            if (digits[i] == 10) {
                digits[i] = 0;
                continue;
            }

            // 没有发生进位
            return digits;
        }

        // 最高位发生进位
        int[] ret = new int[digits.length+1];
        ret[0] = 1;
        return ret;
    }

    public int[] plusOne(int[] digits) {
        int c = 1;  // 进位
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = digits[i] + c;
            if (digits[i] < 10) {
                return digits;
            }
            digits[i] = 0;
            c = 1;
        }

        int[] ret = new int[digits.length + 1];
        ret[0] = 1;
        return ret;
    }
}
