package com.leetcode.tree;


import org.junit.Test;

public class L22ParentheseGenTest {

    @Test
    public void smoke() {
        System.out.println(new L22ParentheseGen().generateParenthesis(1));
        System.out.println(new L22ParentheseGen().generateParenthesis(2));
        System.out.println(new L22ParentheseGen().generateParenthesis(3));
    }
}