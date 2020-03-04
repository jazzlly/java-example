package com.leetcode.hash;


import org.junit.Test;

import java.util.List;

public class L15ThreeSumTest {

    /*
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     */
    @Test
    public void smoke() {
        List<List<Integer>> lists =  L15ThreeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(lists.toString());
    }
}