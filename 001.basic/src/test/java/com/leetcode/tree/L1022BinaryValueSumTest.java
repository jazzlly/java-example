package com.leetcode.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class L1022BinaryValueSumTest {

    @Test
    public void smoke() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n21 = new TreeNode(0);
        TreeNode n22 = new TreeNode(1);

        n1.left = n21;
        n1.right = n22;

        L1022BinaryValueSum test = new L1022BinaryValueSum();
        System.out.println(test.sumRootToLeaf(n1));
    }
}