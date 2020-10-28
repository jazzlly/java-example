package com.leetcode.dp;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class L673LongAscSeqCount {

    public int findNumberOfLIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        // 以num[i]结尾的最长上升子序列的长度
        int[] dp = new int[nums.length];
        // 以num[i]结尾的最长上升子序列的个数
        int[] count = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        // mind debug
        //           i done
        // num 1,2,4,3,5,4,7,2
        // dp  1 2 3 3 1 1 1 1
        //cnt  1,1,1,1,1,1,1,1
        //             i done
        // num 1,2,4,3,5,4,7,2
        // dp  1 2 3 3 4 1 1 1
        //cnt  1,1,1,1,2,1,1,1
        //               i done
        // num 1,2,4,3,5,4,7,2
        // dp  1 2 3 3 4 4 1 1
        //cnt  1,1,1,1,2,1,1,1
        //                 i
        // num 1,2,4,3,5,4,7,2
        // dp  1 2 3 3 4 4 5 1
        //cnt  1,1,1,1,2,1,3,1

        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        int ans = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == max) {
                ans += count[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        L673LongAscSeqCount test = new L673LongAscSeqCount();
//        assertThat(test.findNumberOfLIS(new int[]{})).isEqualTo(0);
//        assertThat(test.findNumberOfLIS(new int[]{1})).isEqualTo(1);
//        assertThat(test.findNumberOfLIS(new int[]{1,2,3})).isEqualTo(1);
        assertThat(test.findNumberOfLIS(new int[]{10,9,2,5,3,7,101,18})).isEqualTo(4);
        assertThat(test.findNumberOfLIS(new int[]{1,2,4,3,5,4,7,2})).isEqualTo(3);
        assertThat(test.findNumberOfLIS(new int[]{1,3,5,4,7})).isEqualTo(2);
        assertThat(test.findNumberOfLIS(new int[]{2,2,2,2,2})).isEqualTo(5);
    }
}
