package com.leetcode.dp;

import javax.swing.plaf.IconUIResource;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class L673LongAscSeqCount {

    public int findNumberOfLIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        // 以num[i]结尾的上升子序列的长度
        int[] dp = new int[nums.length];
        int[] count = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }

        // num 1,2,4,3,5,4,7,2
        // dp  1,2,3,3,4,3,5,1
        //cnt  1,1,1,1,2,1,2,1
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }
                    if (dp[j] + 1 == dp[i]) {
                        count[i] = count[j] + 1;
                    }
                }
            }
        }

        int max = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
            map.put(dp[i], map.getOrDefault(dp[i], 0) + 1);
        }

        return map.get(max);
    }

    public static void main(String[] args) {
        L673LongAscSeqCount test = new L673LongAscSeqCount();
//        assertThat(test.findNumberOfLIS(new int[]{})).isEqualTo(0);
//        assertThat(test.findNumberOfLIS(new int[]{1})).isEqualTo(1);
//        assertThat(test.findNumberOfLIS(new int[]{1,2,3})).isEqualTo(1);
        assertThat(test.findNumberOfLIS(new int[]{1,3,5,4,7})).isEqualTo(1);
        assertThat(test.findNumberOfLIS(new int[]{2,2,2,2,2})).isEqualTo(5);
        assertThat(test.findNumberOfLIS(new int[]{10,9,2,5,3,7,101,18})).isEqualTo(2);
    }
}
