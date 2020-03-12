package com.leetcode.list;

public class L70ClimbStairs {

    public static int climbStairs(int n) {
        switch (n) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                break;
        }

        int p = 1;
        int q = 2;
        int r = 0;
        for (int i = 2; i < n; i++) {
            r = p + q;
            p = q;
            q = r;
        }

        return r;
    }
}
