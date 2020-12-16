package com.leetcode.dp;

public class CoinChange {

    int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }


        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin > amount) {
                continue;
            }
            int tmp = coinChange(coins, amount - coin);
            if (tmp == -1) {
                continue;
            }
            min = Math.min(min, tmp);
        }
        
        return min == Integer.MAX_VALUE ? -1 : (1 + min);
    }

    public static void main(String[] args) {
        CoinChange test = new CoinChange();

        System.out.println(test.coinChange(new int[] {1, 2, 5}, 11));
    }
}
