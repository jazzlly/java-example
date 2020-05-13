package com.leetcode.array.easy;

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
    public int lastRemaining(int n, int m) {
        assert n >= 1;
        assert m >= 1;

        int[] array = new int[n];
        int cnt = 0;
        int pos = 0;

        while (cnt != n - 1) {
            int step = 0;
            while (step < m - 1) {
                pos = (++pos) % n;
                if (array[pos] == -1) {
                    continue;
                }
                step++;
            }
            array[pos] = -1;
            System.out.println(pos);
            cnt++;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] != -1) {
                return i;
            }
        }

        throw new IllegalStateException("no here!");
    }
}
