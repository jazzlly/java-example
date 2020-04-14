package com.leetcode.dp;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L1143LongCommSubStringTest {
    L1143LongCommSubString test = new L1143LongCommSubString();

    /*
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
     */
    @Test
    public void smoke() {
        assertThat(test.longestCommonSubsequence("abcde", "ace")).isEqualTo(3);
        assertThat(test.longestCommonSubsequence("abc", "abc")).isEqualTo(3);
        assertThat(test.longestCommonSubsequence("abc", "def")).isEqualTo(0);
    }
}