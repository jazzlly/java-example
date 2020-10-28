package com.leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 354. 俄罗斯套娃信封问题
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 *
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 说明:
 * 不允许旋转信封。
 *
 * 示例:
 *
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 */
public class L354MaxEnvelopes {

    /* 思路： 对信封按照面积排序
        dp[i]是信封i能够以套娃方式装下的最大信封数量，初始化为1
        dp[i] = d[j] + 1  if  e[i].w > e[j].w && e[i].h > e[j].h and i > j
     */

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length <= 1) {
            return envelopes.length;
        }

        // 先按照长度升序排列，然后按照宽度降序排列
        Arrays.sort(envelopes, (o1, o2) ->
                (o1[0] != o2[0]) ? o1[0] - o2[0] :  o2[1] - o1[1]);

        int[] nums = new int[envelopes.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = envelopes[i][1];
        }

        return lengthOfLIS(nums);
    }

    int lengthOfLIS(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }

        int[] tails = new int[nums.length];
        tails[0] = nums[0];
        int len = 1;

        for (int i = 1; i < nums.length; i++) {
            if (tails[len - 1] < nums[i]) {
                tails[len++] = nums[i];
                continue;
            }
            for (int j = 0; j < len; j++) {
                if (tails[j] >= nums[i] ) {
                    tails[j] = nums[i];
                    break;
                }
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[][] matrix = {{3, 3}, {3, 4}, {1, 2}, {2, 1}, {7, 1}, {5, 8}};
        /*
        [1, 2]
[2, 1]
[3, 4]
[3, 3]
[5, 8]
[7, 1]
         */
        L354MaxEnvelopes test = new L354MaxEnvelopes();
        assertThat(test.maxEnvelopes(matrix)).isEqualTo(3);
    }
}
