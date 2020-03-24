package com.leetcode.tree;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class L94BinaryTreeInOrderTravTest {

    /*
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [1,3,2]
     */
    @Test
    public void smoke() {
        TreeNode l00 = new TreeNode(1);
        TreeNode l10 = new TreeNode(2);
        TreeNode l20 = new TreeNode(3);

        l00.right = l10;
        l10.left = l20;

        assertThat(new L94BinaryTreeInOrderTrav().inorderTraversal(l00))
                .containsExactly(1, 3, 2);

    }
}