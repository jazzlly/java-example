package com.leetcode.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class L653TwoSumBstTest {

    @Test
    public void smoke() {
        TreeNode n11 = new TreeNode(2);
        TreeNode n21 = new TreeNode(1);
        TreeNode n22 = new TreeNode(3);

        n11.left = n21;
        n11.right = n22;

        L653TwoSumBst test = new L653TwoSumBst();
        System.out.println(test.findTarget(n11, 3));
    }
}