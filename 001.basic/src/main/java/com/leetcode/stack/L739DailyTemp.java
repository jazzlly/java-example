package com.leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

public class L739DailyTemp {

    // 给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
    // 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
    //
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];

        // int[0]: 数组的值， int[1] 该元素压缩了多少个元素， int[2] 该元素的index
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            int[] tmp = new int[]{T[i], 0, i};

            while (!stack.empty() && tmp[0] > stack.peek()[0]) {
                int[] pop = stack.pop();
                ans[pop[2]] = tmp[1] + 1;
                tmp[1] += (pop[1] + 1);
            }
            stack.push(tmp);
        }
        return ans;
    }

    public static void main(String[] args) {
        L739DailyTemp test = new L739DailyTemp();

        int[] ans = test.dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73});
        System.out.println(Arrays.toString(ans));

        System.out.println(Arrays.toString(test.dailyTemperatures(
                new int[] {})));

        System.out.println(Arrays.toString(test.dailyTemperatures(
                new int[] {1})));
        System.out.println(Arrays.toString(test.dailyTemperatures(
                new int[] {1,2})));
    }
}
