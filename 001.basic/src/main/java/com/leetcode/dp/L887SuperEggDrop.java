package com.leetcode.dp;

import java.util.Arrays;

public class L887SuperEggDrop {
    public int superEggDrop(int K, int N) {
        int[][]dp = new int[K+1][N+1];

        for (int n = 0; n <= N; n++) {
            dp[1][n] = n;
        }

        for (int k = 2; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                if (n <= k) {
                    dp[k][n] = dp[k-1][n];
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int i = 0; i < n; i++) {
                        min = Math.min(min, Math.max(dp[k-1][i], dp[k][n-i-1]));
                    }
                    dp[k][n] = 1 + min;
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[K][N];
    }


    public static void main(String[] args) {
        L887SuperEggDrop test = new L887SuperEggDrop();
        test.superEggDrop(2, 6);
    }
    
}
