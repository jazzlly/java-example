package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;
import org.junit.Test;

public class L655PrintBinaryTreeTest {

    @Test
    public void smoke() {
        TreeNode n11 = new TreeNode(1);
        TreeNode n21 = new TreeNode(2);
        n11.left = n21;

        L655PrintBinaryTreeRecursion test = new L655PrintBinaryTreeRecursion();
        test.printTree(n11);
    }
}