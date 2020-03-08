package com.leetcode.tree;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L235CommAncestorTest {

    @Test
    public void smoke() {
        TreeNode n00 = new TreeNode(6);

        TreeNode n10 = new TreeNode(2);
        TreeNode n11 = new TreeNode(8);
        n00.left = n10;
        n00.right = n11;

        TreeNode n20 = new TreeNode(0);
        TreeNode n21 = new TreeNode(4);
        n10.left = n20;
        n10.right = n21;

        TreeNode n22 = new TreeNode(7);
        TreeNode n23 = new TreeNode(9);
        n11.left = n22;
        n11.right = n23;

        TreeNode n30 = new TreeNode(3);
        TreeNode n31 = new TreeNode(5);
        n21.left = n30;
        n22.right = n31;

        L235CommAncestor l = new L235CommAncestor();
        assertThat(l.lowestCommonAncestor2(n00, n10, n11).val).isEqualTo(n00.val);
        assertThat(l.lowestCommonAncestor2(n00, n10, n31).val).isEqualTo(n10.val);
        assertThat(l.lowestCommonAncestor2(n00, n20, n31).val).isEqualTo(n10.val);
    }
}