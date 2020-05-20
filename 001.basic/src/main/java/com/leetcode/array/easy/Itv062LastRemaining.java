package com.leetcode.array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题62. 圆圈中最后剩下的数字
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。
 * 求出这个圆圈里剩下的最后一个数字。
 *
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，
 * 则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *
 * 示例 1：
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 *
 * 输入: n = 10, m = 17
 * 输出: 2
 *
 * 限制：
 * 1 <= n <= 10^5
 * 1 <= m <= 10^6
 */
public class Itv062LastRemaining {
    // 朴素思想： 使用数组模拟
    // 超出时间限制 O(N*M)
    public int lastRemaining1(int n, int m) {
        assert n >= 1;
        assert m >= 1;

        int[] nums = new int[n];
        int cursor = 0;

        // 删除n-1个数字
        for (int deleteCount = 0; deleteCount < n - 1; deleteCount++) {

            // 从第一个没有被删除的位置向前移动m-1步
            for (int step = 0; step < m - 1; step++) {
                // 移动到第一个没有删除的位置
                while (nums[cursor] == -1) {
                    cursor = (cursor + 1) % n;
                }

                cursor = (++cursor) % n;

                // 移动到第一个没有删除的位置
                while (nums[cursor] == -1) {
                    cursor = (cursor + 1) % n;
                }
            }

            nums[cursor] = -1;
            System.out.println(cursor);
            cursor = (cursor + 1) % n;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != -1) {
                return i;
            }
        }

        throw new IllegalStateException("no here!");
    }

    // 使用链表模拟
    public int lastRemaining(int n, int m) {
        assert n >= 1;
        assert m >= 1;

        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(i);
        }

        int index = 0;
        while (n > 1) {
            // 向前移动m-1步
            index = (index + m - 1) % n;
            nums.remove(index);
            n--;
        }

        return nums.get(0);
    }
}
