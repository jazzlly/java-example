package com.leetcode.tree;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class L144BinaryTreePreOrder2Test {

    @Test
    public void smoke() {
        TreeNode n00 = new TreeNode(1);
        TreeNode n10 = new TreeNode(2);
        TreeNode n11 = new TreeNode(3);
        TreeNode n20 = new TreeNode(4);
        TreeNode n21 = new TreeNode(6);
        TreeNode n22 = new TreeNode(5);
        TreeNode n30 = new TreeNode(9);

        n21.left = n30;
        n10.left = n20;
        n10.right = n21;
        n11.right = n22;
        n00.left = n10;
        n00.right = n11;

        List<Integer> list = L144BinaryTreePreOrder2.preorderTraversal(n00);
        System.out.println(list);
    }
}