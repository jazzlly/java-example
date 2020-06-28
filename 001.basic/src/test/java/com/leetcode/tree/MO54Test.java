package com.leetcode.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class MO54Test {

    @Test
    public void smoke() {
        TreeNode n1 = new TreeNode(3);
        TreeNode n21 = new TreeNode(1);
        TreeNode n22 = new TreeNode(4);
        n1.left = n21;
        n1.right = n22;

        TreeNode n31 = new TreeNode(2);
        n21.right = n31;

        MO54 test = new MO54();
        System.out.println(test.kthLargest(n1, 1));

    }
}