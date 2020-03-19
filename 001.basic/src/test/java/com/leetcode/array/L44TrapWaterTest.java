package com.leetcode.array;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L44TrapWaterTest {

    @Test
    public void smoke() {
        int water = new L44TrapWater().trap(
                new int[] {0,1,0,2,1,0,1,3,2,1,2,1});
        assertThat(water).isEqualTo(6);
    }




}