package com.leetcode.queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LxxxNumSquares {
    public int numSquares(int n) {
        int sqrt = (int) Math.sqrt(n);

        int[] can = new int[sqrt];
        for (int i = 0; i < sqrt; i++) {
            can[i] = (i + 1) * (i + 1);
        }

        Set<Integer> queue = new HashSet<>();
        queue.add(n);

        int step = 0;
        while (!queue.isEmpty()) {
            Set<Integer> newSet = new HashSet<>();

            for (Integer current : queue) {
                for (int j = 0; j < sqrt; j++) {
                    if (current == can[j]) {
                        return step + 1;
                    }
                    if (current < can[j]) {
                        break;
                    }
                    if (current > can[j]) {
                        newSet.add(current - can[j]);
                    }
                }
            }

            step++;
            queue = newSet;
        }

        return step;
    }

    public static void main(String[] args) {
        System.out.println(Math.sqrt(100));

        LxxxNumSquares test = new LxxxNumSquares();
        System.out.println(test.numSquares(13));
        System.out.println(test.numSquares(12));
        System.out.println(test.numSquares(10));
    }
}
