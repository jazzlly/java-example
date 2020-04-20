package com.leetcode.dp;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L221MaxSquareTest {
    L221MaxSquare test = new L221MaxSquare();
    /**
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     */
    @Test
    public void smoke() {
        char[][] matrix = new char[][] {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        assertThat(test.maximalSquare(matrix)).isEqualTo(4);
    }
}