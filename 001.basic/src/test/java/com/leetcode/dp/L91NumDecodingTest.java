package com.leetcode.dp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L91NumDecodingTest {

    L91NumDecoding test = new L91NumDecoding();

    /*
     * 示例 1:
     * 输入: "12"
     * 输出: 2
     * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
     */
    @Test
    public void smoke() {
        assertThat(test.numDecodings("12")).isEqualTo(2);
    }

    /*
     * 示例 2:
     * 输入: "226"
     * 输出: 3
     * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
     */
    @Test
    public void smoke2() {
        assertThat(test.numDecodings("226")).isEqualTo(3);
    }
}