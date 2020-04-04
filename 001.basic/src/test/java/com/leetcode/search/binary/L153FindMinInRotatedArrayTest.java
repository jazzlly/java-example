package com.leetcode.search.binary;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L153FindMinInRotatedArrayTest {

    @Test
    public void smoke() {
        L153FindMinInRotatedArray test = new L153FindMinInRotatedArray();
        int[] array = new int[]{3, 4, 5, 1, 2};

        assertThat(test.findMin(array)).isEqualTo(1);

        array = new int[]{4, 5, 6, 7, 0, 1, 2};
        assertThat(test.findMin(array)).isEqualTo(0);

        array = new int[]{0, 1, 2, 4, 5, 6, 7};
        assertThat(test.findMin(array)).isEqualTo(0);

        array = new int[]{0};
        assertThat(test.findMin(array)).isEqualTo(0);

        array = new int[]{1, 0};
        assertThat(test.findMin(array)).isEqualTo(0);

    }
}