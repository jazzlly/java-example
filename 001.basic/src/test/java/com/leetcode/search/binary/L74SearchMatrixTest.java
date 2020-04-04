package com.leetcode.search.binary;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class L74SearchMatrixTest {

    /*
     * 输入:
     * matrix = [
     *   [1,   3,  5,  7],
     *   [10, 11, 16, 20],
     *   [23, 30, 34, 50]
     * ]
     * target = 3
     * 输出: true
     */
    @Test
    public void smoke() {
        L74SearchMatrix test = new L74SearchMatrix();
        int[][] matrix = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };

        assertThat(test.searchMatrix(matrix, 1)).isTrue();
        assertThat(test.searchMatrix(matrix, 50)).isTrue();
        assertThat(test.searchMatrix(matrix, 30)).isTrue();
        assertThat(test.searchMatrix(matrix, 12)).isFalse();


    }
}