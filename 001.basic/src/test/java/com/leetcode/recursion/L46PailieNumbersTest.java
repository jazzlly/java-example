package com.leetcode.recursion;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class L46PailieNumbersTest {

    @Test
    public void name() {
        L46PailieNumbers test = new L46PailieNumbers();
        List<List<Integer>> ret = test.permute(new int[] {1, 2, 3});

        System.out.println(ret.toString());
    }
}