package com.leetcode.array;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L189ArrayRotateTest {
    @Test
    public void name() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        L189ArrayRotate.rotate(array, 3);
        assertThat(array).containsExactly(5,6,7,1,2,3,4);
    }

    @Test
    public void name2() {
        int[] array = new int[]{-1, -100, 3, 99};
        L189ArrayRotate.rotate(array, 2);
        assertThat(array).containsExactly(3, 99, -1, -100);
    }
}