package com.leetcode.array;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L22BuyStockTest {

    @Test
    public void smoke() {
        assertThat(new L22BuyStock().maxProfit(
                new int[]{7, 1, 5, 3, 6, 4})).isEqualTo(7);

        assertThat(new L22BuyStock().maxProfit(
                new int[]{1, 2, 3, 4, 5})).isEqualTo(4);

        assertThat(new L22BuyStock().maxProfit(
                new int[]{7, 6, 4, 3, 1})).isEqualTo(0);
    }
}