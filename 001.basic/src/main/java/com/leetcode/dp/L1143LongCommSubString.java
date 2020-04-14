package com.leetcode.dp;

public class L1143LongCommSubString {

    /**
     * 1143. 最长公共子序列
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
     *
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
     *
     * 若这两个字符串没有公共子序列，则返回 0。
     *
     *
     *
     * 示例 1:
     *
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace"，它的长度为 3。
     * 示例 2:
     *
     * 输入：text1 = "abc", text2 = "abc"
     * 输出：3
     * 解释：最长公共子序列是 "abc"，它的长度为 3。
     * 示例 3:
     *
     * 输入：text1 = "abc", text2 = "def"
     * 输出：0
     * 解释：两个字符串没有公共子序列，返回 0。
     *
     *
     * 提示:
     *
     * 1 <= text1.length <= 1000
     * 1 <= text2.length <= 1000
     * 输入的字符串只含有小写英文字符。
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        assert text1 != null && text2 != null &&
                text1.length() >= 1 && text2.length() >=1;

        // 二维数组多申请了一行，一列，避免数组越界
        int[][] grid = new int[text2.length() + 1][text1.length() + 1];
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();

        /* DP方程：
            If S1[-1] != S2[-1]: LCS[s1, s2] = Max(LCS[s1-1, s2], LCS[s1, s2-1])
            If S1[-1] == S2[-1]: LCS[s1, s2] = LCS[s1-1, s2-1] + 1
        */
        for (int i = 1; i <= text2.length(); i++) {
            for (int j = 1; j <= text1.length(); j++) {
                int row = i - 1;
                int col = j - 1;
                if (chars2[row] == chars1[col]) {
                    grid[i][j] = grid[i - 1][j - 1] + 1;
                } else {
                    grid[i][j] = Math.max(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }
        /*
        for (int i = 0; i < grid.length; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }*/
        return grid[text2.length()][text1.length()];
    }
}
