package com.leetcode.tree;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class L105BuildTreeFromPreAndInOrderTest {
    @Test
    public void smoke() {
        TreeNode l0Root = new L105BuildTreeFromPreAndInOrder().buildTree(
                new int[] {3,9,20,15,7}, new int[] {9,3,15,20,7});

        TreeNode l1Left = l0Root.left;
        TreeNode l1Rigth = l0Root.right;
        TreeNode l2Left = l1Rigth.left;
        TreeNode l2Right = l1Rigth.right;

        assertThat(l0Root.val).isEqualTo(3);
        assertThat(l1Left.val).isEqualTo(9);
        assertThat(l1Rigth.val).isEqualTo(20);
        assertThat(l2Left.val).isEqualTo(15);
        assertThat(l2Right.val).isEqualTo(7);
    }
}