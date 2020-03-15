package com.leetcode.array;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L66PlusOneTest {
    @Test
    public void smoke1() {
        assertThat(new L66PlusOne().plusOne(new int[] {1,2,3}))
                .containsExactly(1, 2, 4);
    }

    @Test
    public void smoke2() {
        assertThat(new L66PlusOne().plusOne(new int[] {4,3,2,1}))
                .containsExactly(4,3,2,2);
    }

    @Test
    public void boundary() {
        assertThat(new L66PlusOne().plusOne(new int[] {1}))
                .containsExactly(2);
        assertThat(new L66PlusOne().plusOne(new int[] {9}))
                .containsExactly(1, 0);
        assertThat(new L66PlusOne().plusOne(new int[] {9, 9, 9}))
                .containsExactly(1, 0, 0, 0);
    }

}