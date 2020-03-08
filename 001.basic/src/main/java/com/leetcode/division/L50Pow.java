package com.leetcode.division;

public class L50Pow {

    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     * @param x
     * @param n
     * @return
     */
    public static double myPow1(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == -1) {
            return 1/x;
        }

        if (n % 2 == 0) {
            // n为偶数
            double tmp = myPow1(x, n/2);
            return tmp * tmp;
        } else {
            // n为奇数
            double tmp = myPow1(x, n/2);
            return tmp * tmp * (n > 0 ? x : 1/x);
        }
    }

    public static double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1/x;
            N = -N;
        }

        double ans = 1;
        double current = x;
        for (long i = N; i > 0; i = i/2) {
            if (i % 2 == 1) {
                ans *= current;
            }
            current = current * current;
        }
        return ans;
    }
}
