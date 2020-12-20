package com.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class CoinChange {

    int coinChange1(int[] coins, int amount) {
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

    Map<Integer, Integer> map = new HashMap<>();

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
            int tmp = -1;
            if (map.containsKey(amount - coin)) {
                tmp = map.get(amount - coin);
            } else {
                tmp = coinChange(coins, amount - coin);
                map.put(amount - coin, tmp);
            }
            if (tmp == -1) {
                continue;
            }
            min = Math.min(min, tmp);
        }

        return min == Integer.MAX_VALUE ? -1 : (1 + min);
    }

    public static void main(String[] args) {
        System.out.println(new CoinChange().coinChange(new int[] {2}, 3));
        System.out.println(new CoinChange().coinChange(new int[] {2}, 4));
        System.out.println(new CoinChange().coinChange(new int[] {1, 2, 5}, 11));
        System.out.println(new CoinChange().coinChange(new int[] {1, 2, 5}, 23));
    }
}
