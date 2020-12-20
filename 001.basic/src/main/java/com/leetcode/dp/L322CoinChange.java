package com.leetcode.dp;

import static org.assertj.core.api.Assertions.assertThat;

public class L322CoinChange {
    
    /*
    dp[i] = 1 + min(dp[i-j]) , j in {coins}
    */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        
        for (int i = 1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int c : coins) {
                if (c > i || dp[i -c] == -1) {
                    continue;
                }
                min = Math.min(min, dp[i - c]);
            }
            
            dp[i] = (min == Integer.MAX_VALUE ? -1 : 1 + min);
        }
        
        return dp[amount];
    }
    
    public static void main(String[] args) {
        L322CoinChange test = new L322CoinChange();
        assertThat(test.coinChange(new int[]{1, 2, 5}, 11)).isEqualTo(3);
        assertThat(test.coinChange(new int[]{1, 2, 5}, 25)).isEqualTo(5);
        assertThat(test.coinChange(new int[]{1, 2, 5}, 19)).isEqualTo(5);
        assertThat(test.coinChange(new int[]{2}, 3)).isEqualTo(-1);
        
    }
}
