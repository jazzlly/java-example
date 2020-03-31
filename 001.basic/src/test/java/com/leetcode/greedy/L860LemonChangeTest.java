package com.leetcode.greedy;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * 示例 1：
 * 输入：[5,5,5,10,20]
 * 输出：true
 * 解释：
 * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
 * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
 * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
 * 由于所有客户都得到了正确的找零，所以我们输出 true。
 *
 * 示例 2：
 * 输入：[5,5,10]
 * 输出：true
 * 示例 3：
 *
 * 输入：[10,10]
 * 输出：false
 * 示例 4：
 *
 * 输入：[5,5,10,10,20]
 * 输出：false
 */
public class L860LemonChangeTest {

    @Test
    public void smoke1() {
        assertThat(new L860LemonChange().lemonadeChange(
                new int[]{5, 5, 5, 10, 20})).isTrue();
    }

    @Test
    public void smoke2() {
        assertThat(new L860LemonChange().lemonadeChange(
                new int[]{5, 5, 10})).isTrue();
    }

    @Test
    public void smoke3() {
        assertThat(new L860LemonChange().lemonadeChange(
                new int[]{10, 10})).isFalse();
    }

    @Test
    public void smoke4() {
        assertThat(new L860LemonChange().lemonadeChange(
                new int[]{5, 5, 10, 10, 20})).isFalse();
    }

}