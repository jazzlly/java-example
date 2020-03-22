package com.leetcode.search;

import org.junit.Test;

import java.util.List;

public class L51NQueuesTest {

    @Test
    public void smoke() {
        List<List<String>> result = new L51NQueues().solveNQueens(3);
        System.out.println(result);
        System.out.println("===================");

        for (List<String> strings : result) {
            for (String string : strings) {
                System.out.println(string);
            }
            System.out.println("===================");
        }
    }
}