package com.leetcode.dp;

/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 *
 * 示例 2:
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class L91NumDecoding {

    /**
     * dp[i]为前i为数字序列解码的方式的总数
     * 主状态转移方程：
     *  dp[i] = dp[i-1] + dp[i-2]
     *  考虑分支场景：
     *  s[i] = '0' 不能添加 dp[i-1]
     *  s[i-1][i] 不在 [10,26], 不能添加dp[i-2]
     *
     * 假设输入数字序列为
     *     null  null |  1       1       2       1       2    1
     *  dp-2: ? : null
     *  dp-1: ? : null
     *  dp0':  1 :  [null, 1], [null1]
     *  dp0:  1 :   1
     *  dp1':  2 :  [1,1], | [{null} 11]
     *  dp1:  2 :  [1,1], | [11]
     *  dp2:  3 :  [1,1,2], [11,2] | [1, 12]
     *  dp3:  5 :  [1,1,2,1], [11,2,1] [1, 12,1] | [1,1,21], [11,21]
     */
    public int numDecodings1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] dp = new int[s.length() + 2];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 0; i < s.length(); i++) {
            int dpIndex = i + 2;
            if (s.charAt(i) != '0') {
                dp[dpIndex] =  dp[dpIndex - 1];
            }

            int tmp = charArrayToInt(s, i - 1, i + 1);
            if (tmp >= 10 && tmp <= 26) {
                dp[dpIndex] +=  dp[dpIndex - 2];
            }
        }

        return dp[dp.length - 1];
    }


    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int dp0 = 1;
        int dp1 = 1;
        int dp2 = 0;

        s = "0" + s;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                dp2 += dp1;
            }

            char cprev = s.charAt(i-1);
            char ci = s.charAt(i);
            if (cprev == '1' ||
                    (cprev == '2' && (ci >= '0' && ci <= '6'))) {
                dp2 += dp0;
            }

            dp0 = dp1;
            dp1 = dp2;
            dp2 = 0;
        }

        return dp1;
    }

    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int dp0 = 1;
        int dp1 = 1;
        int dp2 = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                dp2 += dp1;
            }

            int tmp = charArrayToInt(s, i - 1, i + 1);
            if (tmp >= 10 && tmp <= 26) {
                dp2 += dp0;
            }
            dp0 = dp1;
            dp1 = dp2;
            dp2 = 0;
        }

        return dp1;
    }

    int charArrayToInt(String data, int start, int end) {
        if (start < 0) {
            start = 0;
        }

        int result = 0;
        for (int i = start; i < end; i++) {
            int digit = (int) data.charAt(i) - (int) '0';
            assert (digit >= 0 && digit <= 9);
            result *= 10;
            result += digit;
        }
        return result;
    }
}
