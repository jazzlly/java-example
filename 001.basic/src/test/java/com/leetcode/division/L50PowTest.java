package com.leetcode.division;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L50PowTest {

    @Test
    public void smoke() {
        assertThat(L50Pow.myPow(2, 0)).isEqualTo(1);
        assertThat(L50Pow.myPow(2, 1)).isEqualTo(2);
        assertThat(L50Pow.myPow(2, 2)).isEqualTo(4);
        assertThat(L50Pow.myPow(2, 3)).isEqualTo(8);
        assertThat(L50Pow.myPow(2, 4)).isEqualTo(16);
        assertThat(L50Pow.myPow(2, -1)).isEqualTo(0.5);
        assertThat(L50Pow.myPow(2, -2)).isEqualTo(0.25);
        assertThat(L50Pow.myPow(2, -3)).isEqualTo(0.125);

    }
}