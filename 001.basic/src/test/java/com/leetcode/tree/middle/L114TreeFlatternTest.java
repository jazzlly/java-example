package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;
import org.junit.Test;

import static org.junit.Assert.*;

public class L114TreeFlatternTest {

    @Test
    public void smoke() {
        /*
                1
               / \
              2   5
             / \   \
            3   4   6
         */
        TreeNode n1 = new TreeNode(1);

        TreeNode n21 = new TreeNode(2);
        TreeNode n22 = new TreeNode(5);
        n1.left = n21;
        n1.right = n22;

        TreeNode n31 = new TreeNode(3);
        TreeNode n32 = new TreeNode(4);
        TreeNode n33 = new TreeNode(6);
        n21.left = n31;
        n21.right = n32;
        n22.right = n33;

        L114TreeFlattern test = new L114TreeFlattern();
        test.flatten(n1);
    }
}