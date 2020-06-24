package com.leetcode.tree;


import org.junit.Test;

import java.util.Arrays;

public class L501FindModeInBstTest {

    @Test
    public void smoke() {
        TreeNode n1 = new TreeNode(10);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(15);

        n1.left = n2;
        n1.right = n3;
        // n2.right = n3;

        L501FindModeInBst test = new L501FindModeInBst();
        int [] rst = test.findMode(n1);
//        System.out.println(Arrays.toString(rst));
    }

    @Test
    public void smoke2() {
        TreeNode n1 = new TreeNode(2147483647);

        L501FindModeInBst test = new L501FindModeInBst();
        int [] rst = test.findMode(n1);
        System.out.println(Arrays.toString(rst));
    }
}