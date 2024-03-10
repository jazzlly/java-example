package com.leetcode.division;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L50PowTest {

    @Test
    public void smoke() {
        assertThat(new L50Pow2().myPow(2, 1)).isEqualTo(2);
        assertThat(new L50Pow2().myPow(2, 2)).isEqualTo(4);
        assertThat(new L50Pow2().myPow(2, 0)).isEqualTo(1);
        assertThat(new L50Pow2().myPow(2, 3)).isEqualTo(8);
        assertThat(new L50Pow2().myPow(2, 4)).isEqualTo(16);
        assertThat(new L50Pow2().myPow(2, -1)).isEqualTo(0.5);
        assertThat(new L50Pow2().myPow(2, -2)).isEqualTo(0.25);
        assertThat(new L50Pow2().myPow(2, -3)).isEqualTo(0.125);
    }

    @Test
    public void failed() {
        assertThat(new L50Pow2().myPow(2.0, -2147483648))
                .isEqualTo(Math.pow(2.0, -2147483648));

    }
}