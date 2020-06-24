package com.leetcode.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class L437TreePath3Test {

    @Test
    public void name() {
        TreeNode n1 = new TreeNode(10);

        TreeNode n21 = new TreeNode(5);
        TreeNode n22 = new TreeNode(-3);
        n1.left = n21;
        n1.right = n22;

        TreeNode n31 = new TreeNode(3);
        TreeNode n32 = new TreeNode(2);
        TreeNode n33 = new TreeNode(11);
        n21.left = n31;
        n21.right = n32;
        n22.right = n33;

        TreeNode n41 = new TreeNode(3);
        TreeNode n42 = new TreeNode(-2);
        TreeNode n43 = new TreeNode(1);

        n31.left = n41;
        n31.right = n42;
        n32.right = n43;

        TreeNode n51 = new TreeNode(7);
        n42.left = n51;

        L437TreePath3 test = new L437TreePath3();
        System.out.println(test.pathSum(n1, 8));
    }
}